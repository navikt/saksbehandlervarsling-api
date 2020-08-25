package no.nav.saksbehandlervarsling_api.utils;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public class DbTestUtils {

    // Rekkefølgen er viktig pga foreign key constraints
    private final static List<String> ALL_TABLES = Collections.EMPTY_LIST;

    public static void testMigrate (DataSource dataSource) {
        Flyway.configure()
                .dataSource(dataSource)
                .baselineOnMigrate(true)
                .load()
                .migrate();
    }

    public static void cleanupDb(JdbcTemplate db) {
        ALL_TABLES.forEach((table) -> deleteAllFromTable(db, table));
    }

    private static void deleteAllFromTable(JdbcTemplate db, String tableName) {
        db.execute("DELETE FROM " + tableName);
    }

}
