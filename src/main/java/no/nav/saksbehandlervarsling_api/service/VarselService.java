package no.nav.saksbehandlervarsling_api.service;

import no.nav.saksbehandlervarsling_api.controller.domain.SaksbehandlerOpprettVarselDTO;
import no.nav.saksbehandlervarsling_api.controller.domain.SystemOpprettVarselDTO;
import no.nav.saksbehandlervarsling_api.controller.domain.VarselDTO;
import no.nav.saksbehandlervarsling_api.repository.VarselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VarselService {

    private final VarselRepository varselRepository;

    @Autowired
    public VarselService(VarselRepository varselRepository) {
        this.varselRepository = varselRepository;
    }

    public List<VarselDTO> hentVarslinger(String veilederId) {
        return varselRepository.hentVarselForVeileder(veilederId)
                .stream()
                .map((v) -> new VarselDTO())
                .collect(Collectors.toList());
    }

    public void opprettVarsel(SystemOpprettVarselDTO systemOpprettVarselDTO) {
        // TODO: Valider
    }

    public void opprettVarsel(SaksbehandlerOpprettVarselDTO saksbehandlerOpprettVarselDTO) {
        // TODO: Valider
    }

}
