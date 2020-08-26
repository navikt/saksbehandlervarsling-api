package no.nav.saksbehandlervarsling_api.repository.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Varsling {
    long id;
    String saksbehandlerId;
    long varselId;
    boolean erLest;
    LocalDateTime datoLest;
}
