package com.ua.ecwo.repos

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.config.ApplicationConfig
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.coroutines.experimental.CoroutineContext


object DatabaseFactory {

    fun initOracle() {
        Database.connect(
                "jdbc:oracle:thin:@ann:1521:acco2018",
                "oracle.jdbc.driver.OracleDriver",
                "APP", "APP")
//                .let { manager: (Database) -> TransactionManager = { ThreadLocalTransactionManager(it, java.sql.Connection.TRANSACTION_READ_COMMITTED) } }
    }

    fun initH2() {
        Database.connect("jdbc:h2:tcp://localhost/~/test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver", user = "sa")
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

    fun initHikari(config: ApplicationConfig) {
        val properties = Properties()
        properties.setProperty("user", config.config("dataSource").property("user").getString())
        properties.setProperty("url", config.config("dataSource").property("url").getString())
        val hikariConfig = HikariConfig(properties)
        val ds = HikariDataSource(hikariConfig)
        Database.connect(ds)
        /*val config = HikariConfig()
                .apply {
                    driverClassName = "org.h2.Driver"
                    jdbcUrl = "jdbc:h2:mem:test"
                    maximumPoolSize = 3
                    isAutoCommit = false
                    transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                }
        config.validate()
        return HikariDataSource(config)*/
    }

    private val dispatcher: CoroutineContext

    init {
        dispatcher = newFixedThreadPoolContext(5, "database-pool")
    }

    suspend fun <T> dbQuery(
            block: () -> T): T =
            withContext(dispatcher) {
                transaction(java.sql.Connection.TRANSACTION_READ_COMMITTED, 3) {
                    //                transaction() {
                    addLogger(StdOutSqlLogger)
                    block()
                }
            }
}