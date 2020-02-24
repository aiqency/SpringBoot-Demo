package com.aiqency.springbootdemo.testcontainer

import com.aiqency.springbootdemo.testcontainer.postgres.MPostgresContainer
import org.junit.ClassRule
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.junit.jupiter.Container

open class BaseTc {

    companion object {

        @Container
        @JvmField
        val postgresContainer: MPostgresContainer = MPostgresContainer.instance
                .withDatabaseName("integration-tests-db")
                .withUsername("sa")
                .withPassword("sa")

        @ConditionalOnProperty(prefix = "security", value = ["disabled"], havingValue = "true")
        class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
            override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
                TestPropertyValues.of(
                        "spring.datasource.url=${postgresContainer.jdbcUrl}",
                        "spring.datasource.username=${postgresContainer.username}",
                        "spring.datasource.password=${postgresContainer.password}"
                ).applyTo(configurableApplicationContext.environment)
            }
        }
    }
}