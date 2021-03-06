package no.nav.saksbehandlervarsling_api.utils;

public class SingletonPostgresContainer {

    private static PostgresContainer postgresContainer;

    public static PostgresContainer init() {
        if (postgresContainer == null) {
            postgresContainer = new PostgresContainer();
            postgresContainer.getContainer().start();
            DbTestUtils.testMigrate(postgresContainer.getDataSource());
            setupShutdownHook();
        }

        return postgresContainer;
    }

    private static void setupShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (postgresContainer != null && postgresContainer.getContainer().isRunning()) {
                postgresContainer.getContainer().stop();
            }
        }));
    }
}
