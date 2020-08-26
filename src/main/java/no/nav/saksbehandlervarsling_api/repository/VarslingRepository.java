package no.nav.saksbehandlervarsling_api.repository;

import lombok.SneakyThrows;
import no.nav.saksbehandlervarsling_api.repository.domain.Varsling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Repository
public class VarslingRepository {

    public static final String VARSLING_TABLE = "VARSLING";

    public static final String ID = "ID";

    public static final String SAKSBEHANDLER_ID = "SAKSBEHANDLER_ID";
    public static final String VARSEL_ID = "VARSEL_ID";
    public static final String ER_LEST = "ER_LEST";
    public static final String DATO_LEST = "DATO_LEST";

    private final JdbcTemplate db;

    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public VarslingRepository(JdbcTemplate db) {
        this.db = db;
        simpleJdbcInsert = new SimpleJdbcInsert(db.getDataSource())
                .withTableName(VARSLING_TABLE)
                .usingGeneratedKeyColumns(ID);
    }

    public List<Varsling> hentVarslingerForSaksbehandler(String saksbehandlerId) {
        String sql = format("SELECT * FROM %s WHERE %s = ?", VARSLING_TABLE, SAKSBEHANDLER_ID);
        return db.query(sql, VarslingRepository::mapVarslsing, saksbehandlerId);
    }

    public void opprettVarsling(String saksbehandlerId, long varselId) {
        Map<String, Object> parameters = new HashMap<>(4);
        parameters.put(SAKSBEHANDLER_ID, saksbehandlerId);
        parameters.put(VARSEL_ID, varselId);

        simpleJdbcInsert.execute(parameters);
    }

    @SneakyThrows
    private static Varsling mapVarslsing(ResultSet rs, int rowNum) {
        return new Varsling()
                .setId(rs.getLong(ID))
                .setSaksbehandlerId(rs.getString(SAKSBEHANDLER_ID))
                .setVarselId(rs.getLong(VARSEL_ID))
                .setErLest(rs.getBoolean(ER_LEST))
                .setDatoLest(ofNullable(rs.getTimestamp(DATO_LEST)).map(Timestamp::toLocalDateTime).orElse(null));
    }

}
