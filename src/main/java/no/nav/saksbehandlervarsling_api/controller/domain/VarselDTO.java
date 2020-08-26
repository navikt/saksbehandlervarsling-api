package no.nav.saksbehandlervarsling_api.controller.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class VarselDTO {
    long id;
    String varselNavn;
    String tittel;
    String beskrivelse;
    String opprettetAv;
    LocalDateTime datoOpprettet;
    LocalDateTime datoLest;
    boolean erLest;
}
