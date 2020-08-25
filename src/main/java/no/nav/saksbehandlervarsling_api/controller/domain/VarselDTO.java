package no.nav.saksbehandlervarsling_api.controller.domain;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class VarselDTO {
    long id;
    String varselNavn;
    String tittel;
    String beskrivelse;
    String opprettetAv;
    ZonedDateTime datoOpprettet;
    ZonedDateTime datoLest;
    boolean erLest;
}
