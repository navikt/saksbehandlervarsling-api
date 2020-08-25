package no.nav.saksbehandlervarsling_api.controller;

import no.nav.saksbehandlervarsling_api.controller.domain.SaksbehandlerOpprettVarselDTO;
import no.nav.saksbehandlervarsling_api.service.AuthService;
import no.nav.saksbehandlervarsling_api.service.VarselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity opprettVarsel(@RequestBody SaksbehandlerOpprettVarselDTO saksbehandlerOpprettVarselDTO) {
        authService.skalVereSystemBruker();
        varselService.opprettVarsel(saksbehandlerOpprettVarselDTO);
        return ResponseEntity.noContent().build();
    }

}
