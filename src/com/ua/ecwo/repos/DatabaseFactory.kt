package com.ua.ecwo.repos

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.coroutines.experimental.CoroutineContext


object DatabaseFactory {

    fun init() {
        // Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
//        Database.connect(hikari())
        Database.connect(
                "jdbc:oracle:thin:@ann:1521:acco2018",
                "oracle.jdbc.driver.OracleDriver",
                "APP", "APP")
    }


    private fun hikari(): HikariDataSource {

        val config = HikariConfig()
                .apply {
                    driverClassName = "org.h2.Driver"
                    jdbcUrl = "jdbc:h2:mem:test"
                    maximumPoolSize = 3
                    isAutoCommit = false
                    transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                }
        config.validate()
        return HikariDataSource(config)
    }

    private val dispatcher: CoroutineContext

    init {

        dispatcher = newFixedThreadPoolContext(5, "database-pool")

    }

    suspend fun <T> dbQuery(
            block: () -> T): T =
            withContext(dispatcher) {
                transaction { block() }
            }
}