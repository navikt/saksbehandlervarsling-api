package no.nav.saksbehandlervarsling_api.controller;

import no.nav.saksbehandlervarsling_api.controller.domain.SystemOpprettVarselDTO;
import no.nav.saksbehandlervarsling_api.service.AuthService;
import no.nav.saksbehandlervarsling_api.service.VarselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/system/varsel")
public class SystemVarselController {

    private final AuthService authService;

    private final VarselService varselService;

    @Autowired
    public SystemVarselController(AuthService authService, VarselService varselService) {
        this.authService = authService;
        this.varselService = varselService;
    }

    @PostMapping
    public ResponseEntity opprettVarsel(@Valid @RequestBody SystemOpprettVarselDTO systemOpprettVarselDTO) {
        authService.skalVereSystemBruker();
        varselService.opprettVarsel(authService.getInnloggetBrukerSubject(), systemOpprettVarselDTO);
        return ResponseEntity.noContent().build();
    }

}
