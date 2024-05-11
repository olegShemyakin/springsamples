package org.akira.extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Создание расширения для @ExtendWith
 */

public class PostgreSQLExtension implements BeforeAllCallback, AfterAllCallback {

    private static final String DATABASE_NAME = "book_store";
    private static final String DB_SCHEMA = "book_store";
    private static final String USER_NAME = "postgres";

    private PostgreSQLContainer<?> postgres;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        postgres = new PostgreSQLContainer<>("postgres:16.1-alpine3.19")
                        .withReuse(Boolean.TRUE)
                        .withDatabaseName(DATABASE_NAME)
                        .withUsername(USER_NAME);

        postgres.start();
        System.setProperty("DB_URL", postgres.getJdbcUrl());
        System.setProperty("DB_USER", postgres.getUsername());
        System.setProperty("DB_PASSWORD", postgres.getPassword());
        System.setProperty("DB_SCHEMA", DB_SCHEMA);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        postgres.stop(); //без этого не останавливает контейнер
    }
}
