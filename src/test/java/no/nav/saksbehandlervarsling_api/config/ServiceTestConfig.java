package no.nav.saksbehandlervarsling_api.config;


import no.nav.saksbehandlervarsling_api.service.AuthService;
import no.nav.saksbehandlervarsling_api.service.VarselService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        AuthService.class,
        VarselService.class
})
public class ServiceTestConfig {}
