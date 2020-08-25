package no.nav.saksbehandlervarsling_api.config;

import no.nav.saksbehandlervarsling_api.repository.VarselRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        VarselRepository.class
})
public class RepositoryTestConfig {}
