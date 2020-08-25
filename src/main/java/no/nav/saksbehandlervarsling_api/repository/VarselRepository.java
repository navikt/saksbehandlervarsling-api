package no.nav.saksbehandlervarsling_api.repository;

import no.nav.saksbehandlervarsling_api.repository.domain.Varsel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
public class VarselRepository {

    public List<Varsel> hentVarselForVeileder(String veilederId) {
        return Collections.emptyList();
    }

    @Transactional
    public void opprettVarsel(String opprettetAv, String varselNavn, String varselTittel, String varselBeskrivelse) {

        // Opprett Varsel

        // Opprett varsling

    }

}
