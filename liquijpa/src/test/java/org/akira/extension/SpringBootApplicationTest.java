package org.akira.extension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Настройка теста с использованием установки свойств через @ContextConfiguration
 */

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = {SpringBootApplicationTest.Initializer.class})
public class SpringBootApplicationTest {

    private static final String DATABASE_NAME = "book_store";
    private static final String DB_SCHEMA = "book_store";
    private static final String USER_NAME = "postgres";
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:16.1-alpine3.19")
                    .withReuse(Boolean.TRUE)
                    .withDatabaseName(DATABASE_NAME)
                    .withUsername(USER_NAME);

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                "DB_URL=" + postgreSQLContainer.getJdbcUrl(),
                    "DB_USER=" + postgreSQLContainer.getUsername(),
                    "DB_PASSWORD=" + postgreSQLContainer.getPassword(),
                    "DB_SCHEMA=" + DB_SCHEMA
            ).applyTo(applicationContext.getEnvironment());
        }
    }
}
