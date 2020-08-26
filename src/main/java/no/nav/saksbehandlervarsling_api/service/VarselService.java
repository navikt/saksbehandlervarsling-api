package no.nav.saksbehandlervarsling_api.service;

import no.nav.saksbehandlervarsling_api.controller.domain.SaksbehandlerOpprettVarselDTO;
import no.nav.saksbehandlervarsling_api.controller.domain.SystemOpprettVarselDTO;
import no.nav.saksbehandlervarsling_api.controller.domain.VarselDTO;
import no.nav.saksbehandlervarsling_api.repository.VarselRepository;
import no.nav.saksbehandlervarsling_api.repository.VarslingRepository;
import no.nav.saksbehandlervarsling_api.repository.domain.Varsel;
import no.nav.saksbehandlervarsling_api.repository.domain.Varsling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VarselService {

    private final VarslingRepository varslingRepository;

    private final VarselRepository varselRepository;

    @Autowired
    public VarselService(VarslingRepository varslingRepository, VarselRepository varselRepository) {
        this.varslingRepository = varslingRepository;
        this.varselRepository = varselRepository;
    }

    public List<VarselDTO> hentVarslinger(String saksbehandlerId) {
        List<Varsling> varslinger = varslingRepository.hentVarslingerForSaksbehandler(saksbehandlerId);
        List<Long> varselIdList = varslinger.stream().map(Varsling::getVarselId).collect(Collectors.toList());
        List<Varsel> varseler = varselRepository.hentVarseler(varselIdList);

        return varslinger
                .parallelStream()
                .map(varsling -> {
                    Optional<Varsel> kanskjeVarsel = varseler
                            .stream()
                            .filter(v -> v.getId() == varsling.getVarselId())
                            .findFirst();

                    return kanskjeVarsel.map(v -> new VarselDTO()
                            .setId(varsling.getId())
                            .setVarselNavn(v.getNavn())
                            .setTittel(v.getTittel())
                            .setBeskrivelse(v.getBeskrivelse())
                            .setOpprettetAv(v.getOpprettetAv())
                            .setErLest(varsling.isErLest())
                            .setDatoLest(varsling.getDatoLest())).orElse(null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Transactional
    public void opprettVarsel(String systembrukerNavn, SystemOpprettVarselDTO systemOpprettVarselDTO) {
        long varselId = varselRepository.opprettVarsel(
                systemOpprettVarselDTO.getVarselNavn(), systemOpprettVarselDTO.getTittel(),
                systemOpprettVarselDTO.getBeskrivelse(), systembrukerNavn
        );

        varslingRepository.opprettVarsling(systemOpprettVarselDTO.getSaksbehandlerId(), varselId);
    }

    public void opprettVarsel(String saksbehandlerId, SaksbehandlerOpprettVarselDTO saksbehandlerOpprettVarselDTO) {
        // TODO: Kan fjernes, veileder trenger ikke å opprette varsel. Alarm
    }

}
