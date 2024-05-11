package org.akira.extension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Настройка теста с использованием установки свойств через @DynamicPropertySource
 */

@SpringBootTest
@Testcontainers
public class SpringBootApplicationDynamicTest {

    private static final String DATABASE_NAME = "book_store";
    private static final String DB_SCHEMA = "book_store";
    private static final String USER_NAME = "postgres";
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:16.1-alpine3.19")
                    .withReuse(Boolean.TRUE)
                    .withDatabaseName(DATABASE_NAME)
                    .withUsername(USER_NAME);

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("DB_URL", () -> postgreSQLContainer.getJdbcUrl());
        registry.add("DB_USER", () -> postgreSQLContainer.getUsername());
        registry.add("DB_PASSWORD", () -> postgreSQLContainer.getPassword());
        registry.add("DB_SCHEMA", () -> DB_SCHEMA);
    }
}
