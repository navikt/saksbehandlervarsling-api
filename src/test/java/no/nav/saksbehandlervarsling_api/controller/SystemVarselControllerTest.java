package no.nav.saksbehandlervarsling_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.nav.saksbehandlervarsling_api.controller.domain.SystemOpprettVarselDTO;
import no.nav.saksbehandlervarsling_api.service.AuthService;
import no.nav.saksbehandlervarsling_api.service.VarselService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SystemVarselController.class)
public class SystemVarselControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @MockBean
    private VarselService varselService;

    @Test
    public void opprettVarsel_skal_sjekke_om_systembruker() throws Exception {
        SystemOpprettVarselDTO opprettVarselDTO = new SystemOpprettVarselDTO()
                .setVarselNavn("navn")
                .setBeskrivelse("beskrivelse")
                .setTittel("tittel")
                .setSaksbehandlerId("saksbehandlerId");

        mockMvc.perform(post("/api/system/varsel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(opprettVarselDTO)));

        verify(authService, times(1)).skalVereSystemBruker();
    }

    @Test
    public void opprettVarsel_skal_opprette_varsel() throws Exception {
        when(authService.getInnloggetBrukerSubject()).thenReturn("srvtest");

        SystemOpprettVarselDTO opprettVarselDTO = new SystemOpprettVarselDTO()
                .setVarselNavn("navn")
                .setBeskrivelse("beskrivelse")
                .setTittel("tittel")
                .setSaksbehandlerId("saksbehandlerId");

        mockMvc.perform(post("/api/system/varsel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(opprettVarselDTO)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<SystemOpprettVarselDTO> userCaptor = ArgumentCaptor.forClass(SystemOpprettVarselDTO.class);
        verify(varselService, times(1)).opprettVarsel(anyString(), userCaptor.capture());

        SystemOpprettVarselDTO capturedVarselDTO = userCaptor.getValue();
        assertEquals(opprettVarselDTO.getVarselNavn(), capturedVarselDTO.getVarselNavn());
        assertEquals(opprettVarselDTO.getBeskrivelse(), capturedVarselDTO.getBeskrivelse());
        assertEquals(opprettVarselDTO.getTittel(), capturedVarselDTO.getTittel());
        assertEquals(opprettVarselDTO.getSaksbehandlerId(), capturedVarselDTO.getSaksbehandlerId());
    }

    @Test
    public void opprettVarsel_skal_validere_felt() throws Exception {
        mockMvc.perform(post("/api/system/varsel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(
                        new SystemOpprettVarselDTO()
                            .setVarselNavn("")
                            .setBeskrivelse("beskrivelse")
                            .setTittel("tittel")
                            .setSaksbehandlerId("saksbehandlerId")
                        )))
                .andExpect(status().isBadRequest());

        mockMvc.perform(post("/api/system/varsel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(
                        new SystemOpprettVarselDTO()
                                .setVarselNavn("navn")
                                .setBeskrivelse(null)
                                .setTittel("tittel")
                                .setSaksbehandlerId("saksbehandlerId")
                )))
                .andExpect(status().isBadRequest());

        mockMvc.perform(post("/api/system/varsel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(
                        new SystemOpprettVarselDTO()
                                .setVarselNavn("navn")
                                .setBeskrivelse("saksbehandlerId")
                                .setTittel("")
                                .setSaksbehandlerId("veilederId")
                )))
                .andExpect(status().isBadRequest());


        mockMvc.perform(post("/api/system/varsel")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(
                        new SystemOpprettVarselDTO()
                                .setVarselNavn("navn")
                                .setBeskrivelse("beskrivelse")
                                .setTittel("tittel")
                                .setSaksbehandlerId("")
                )))
                .andExpect(status().isBadRequest());
    }

}
