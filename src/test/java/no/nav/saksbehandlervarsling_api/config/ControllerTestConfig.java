package no.nav.saksbehandlervarsling_api.config;

import no.nav.saksbehandlervarsling_api.controller.InternalController;
import no.nav.saksbehandlervarsling_api.controller.SaksbehandlerVarselController;
import no.nav.saksbehandlervarsling_api.controller.SystemVarselController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        InternalController.class,
        SaksbehandlerVarselController.class,
        SystemVarselController.class
})
public class ControllerTestConfig {
}
