package no.nav.saksbehandlervarsling_api.service;

import lombok.extern.slf4j.Slf4j;
import no.nav.common.auth.subject.IdentType;
import no.nav.common.auth.subject.Subject;
import no.nav.common.auth.subject.SubjectHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.String.format;

@Slf4j
@Service
public class AuthService {

    public String getInnloggetVeilederIdent() {
        return SubjectHandler
                .getIdent()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fant ikke ident for innlogget veileder"));
    }

    public void skalVereInternBruker() {
        skalVere(IdentType.InternBruker);
    }

    public void skalVereSystemBruker() {
        skalVere(IdentType.Systemressurs);
    }

    public String getInnloggetBrukerSubject() {
        return SubjectHandler
                .getSubject()
                .map(Subject::getUid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Fant ikke subject"));
    }

    private void skalVere(IdentType forventetIdentType) {
        IdentType identType = SubjectHandler.getIdentType().orElse(null);
        if (identType != forventetIdentType) {
            log.warn(format("Forventet bruker av type %s, men fikk %s", identType, forventetIdentType));
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

}
