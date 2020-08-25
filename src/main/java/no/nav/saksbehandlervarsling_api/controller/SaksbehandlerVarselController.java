package no.nav.saksbehandlervarsling_api.controller;

import no.nav.saksbehandlervarsling_api.controller.domain.SaksbehandlerOpprettVarselDTO;
import no.nav.saksbehandlervarsling_api.controller.domain.VarselDTO;
import no.nav.saksbehandlervarsling_api.service.AuthService;
import no.nav.saksbehandlervarsling_api.service.VarselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saksbehandler/varsel")
public class SaksbehandlerVarselController {

    private final VarselService varselService;

    private final AuthService authService;

    @Autowired
    public SaksbehandlerVarselController(VarselService varselService, AuthService authService) {
        this.varselService = varselService;
        this.authService = authService;
    }

    @GetMapping
    public List<VarselDTO> hentVarslingerForInnloggetVeileder() {
        authService.skalVereInternBruker();

        String veilederId = authService.getInnloggetVeilederIdent();
        return varselService.hentVarslinger(veilederId);
    }

    @PostMapping
    public ResponseEntity opprettVarsel(@RequestBody SaksbehandlerOpprettVarselDTO saksbehandlerOpprettVarselDTO) {
        authService.skalVereInternBruker();
        varselService.opprettVarsel(saksbehandlerOpprettVarselDTO);
        return ResponseEntity.noContent().build();
    }


}
