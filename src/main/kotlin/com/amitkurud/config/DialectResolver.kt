/*
 * Copyright (c) 2020. Amit Kurud
 */
package com.amitkurud.config

import org.springframework.data.jdbc.repository.config.DialectResolver.JdbcDialectProvider
import org.springframework.data.relational.core.dialect.Dialect
import org.springframework.data.relational.core.dialect.MySqlDialect
import org.springframework.jdbc.core.ConnectionCallback
import org.springframework.jdbc.core.JdbcOperations
import java.sql.Connection
import java.sql.SQLException
import java.util.*

class DialectResolver : JdbcDialectProvider {
    override fun getDialect(operations: JdbcOperations): Optional<Dialect> {
        return Optional.ofNullable(
                operations.execute { connection: Connection -> getDialect(connection) })
    }

    companion object {
        @Throws(SQLException::class)
        private fun getDialect(connection: Connection): Dialect? {
            val metaData = connection.metaData
            val name = metaData.databaseProductName.toLowerCase(Locale.ROOT)
            return if (name.contains("mariadb")) {
                MySqlDialect.INSTANCE
            } else null
        }
    }
}