package no.nav.saksbehandlervarsling_api.controller.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class SystemOpprettVarselDTO {
    @NotBlank
    String varselNavn;

    @NotBlank
    String tittel;

    @NotNull
    String beskrivelse;

    @NotBlank
    String veilederId;
}
