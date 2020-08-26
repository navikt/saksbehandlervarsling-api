package no.nav.saksbehandlervarsling_api.repository;

import lombok.SneakyThrows;
import no.nav.saksbehandlervarsling_api.repository.domain.Varsel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Repository
public class VarselRepository {


    public static final String VARSEL_TABLE = "VARSEL";

    public static final String ID = "ID";
    public static final String NAVN = "NAVN";
    public static final String TITTEL = "TITTEL";
    public static final String BESKRIVELSE = "BESKRIVELSE";
    public static final String DATO_OPPRETTET = "DATO_OPPRETTET";
    public static final String OPPRETTET_AV = "OPPRETTET_AV";

    private final JdbcTemplate db;

    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public VarselRepository(JdbcTemplate db) {
        this.db = db;
        simpleJdbcInsert = new SimpleJdbcInsert(db.getDataSource())
                .withTableName(VARSEL_TABLE)
                .usingGeneratedKeyColumns(ID);
    }

    public Varsel hentVarsel(long varselId) {
        String sql = format("SELECT * FROM %s WHERE %s = ?", VARSEL_TABLE, ID);
        return db.queryForObject(sql, VarselRepository::mapVarsel, varselId);
    }

    public List<Varsel> hentVarseler(List<Long> varselIder) {
        // TODO: Søk etter varsler

       return Collections.emptyList();
    }

    @Transactional
    public long opprettVarsel(String navn, String tittel, String beskrivelse, String opprettetAv) {
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put(NAVN, navn);
        parameters.put(TITTEL, tittel);
        parameters.put(BESKRIVELSE, beskrivelse);
        parameters.put(OPPRETTET_AV, opprettetAv);

        return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
    }

    @SneakyThrows
    private static Varsel mapVarsel(ResultSet rs, int rowNum) {
        return new Varsel()
                .setId(rs.getLong(ID))
                .setNavn(rs.getString(NAVN))
                .setTittel(rs.getString(TITTEL))
                .setBeskrivelse(rs.getString(BESKRIVELSE))
                .setOpprettetAv(rs.getString(OPPRETTET_AV))
                .setDatoOpprettet(rs.getTimestamp(DATO_OPPRETTET).toLocalDateTime());
    }

}
