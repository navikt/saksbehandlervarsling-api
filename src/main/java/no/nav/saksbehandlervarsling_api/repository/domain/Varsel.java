package no.nav.saksbehandlervarsling_api.repository.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class Varsel {
    long id;
    String navn;
    String tittel;
    String beskrivelse;
    LocalDateTime datoOpprettet;
    String opprettetAv;
}
