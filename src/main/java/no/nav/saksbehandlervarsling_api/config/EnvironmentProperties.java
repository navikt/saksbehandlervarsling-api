package no.nav.saksbehandlervarsling_api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.env")
public class EnvironmentProperties {

    private String openAmDiscoveryUrl;

    private String openAmClientId;

    private String openAmRefreshUrl;

    private String openAmRedirectUrl;


    private String aadDiscoveryUrl;

    private String aadClientId;


    private String naisStsDiscoveryUrl;

    private String naisStsClientId;


    private String dbUrl;

}
