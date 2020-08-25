package no.nav.saksbehandlervarsling_api.controller.domain;

import lombok.Data;

@Data
public class SystemOpprettVarselDTO {
    String varselNavn;
    String tittel;
    String beskrivelse;
    String veilederId;
}
