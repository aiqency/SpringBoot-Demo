package com.aiqency.springbootdemo.testcontainer.postgres

import org.testcontainers.containers.PostgreSQLContainer


class MPostgresContainer private constructor(): PostgreSQLContainer<MPostgresContainer>(IMAGE_VERSION) {

    override fun start() {
        super.start()
        System.setProperty("DB_URL", container!!.jdbcUrl)
        System.setProperty("DB_USERNAME", container!!.username)
        System.setProperty("DB_PASSWORD", container!!.password)
    }

    override fun stop() { //do nothing, JVM handles shut down
    }

    companion object {
        private const val IMAGE_VERSION = "postgres:11.1"
        private var container: MPostgresContainer? = null
        val instance: MPostgresContainer
            get() {
                if (container == null) {
                    container = MPostgresContainer()
                }
                return container!!
            }
    }
}