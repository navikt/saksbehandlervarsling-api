package no.nav.saksbehandlervarsling_api;

import no.nav.saksbehandlervarsling_api.config.ApplicationTestConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import(ApplicationTestConfig.class)
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(TestApplication.class);
        application.setAdditionalProfiles("local");
        application.run(args);
    }

}