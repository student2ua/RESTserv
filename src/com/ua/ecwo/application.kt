package com.ua.ecwo

import com.codahale.metrics.JmxReporter
import com.fasterxml.jackson.databind.SerializationFeature
import com.ua.ecwo.repos.DatabaseFactory
import com.ua.ecwo.repos.IKTRepoImpl_asSample
import com.ua.ecwo.repos.ScheduleInetRepoMockImpl
import com.ua.ecwo.routing.pAPI_ikt
import com.ua.ecwo.routing.schedule17
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.metrics.Metrics
import io.ktor.pipeline.PipelineContext
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.logging.LogManager

fun main(args: Array<String>): Unit = io.ktor.server.netty.DevelopmentEngine.main(args)

/** @see https://github.com/JetBrains/kotlinconf-app/blob/master/backend/src/org/jetbrains/kotlinconf/backend/Main.kt*/

@Suppress("unused") // Referenced in application.conf
fun Application.module() {

    val config = environment.config.config("service")
    val mode = config.property("environment").getString()
    log.info("Environment: $mode")
    val production = mode == "production"

    if (!production) {
        install(CallLogging)
        //    h2
        if (environment.config.propertyOrNull("db_type")?.getString().equals("h2", ignoreCase = true)) {
            DatabaseFactory.initH2()
        } else {
            val configDB = environment.config.config(environment.config.propertyOrNull("db_type")?.getString() ?: "h2")
            DatabaseFactory.initHikari(configDB)
        }

    } else {
        //oracle
        val file = File("E:\\project\\KT\\RESTserv\\resources\\OracleLog.properties")
        LogManager.getLogManager().readConfiguration(/*this.javaClass::class.java.getResourceAsStream(*/file.inputStream())
        DatabaseFactory.initOracle()
    }

    install(DefaultHeaders) {
        header("X-Engine", "Ktor/Netty") // will send this header with each response
    }
    install(CORS) {
        /*method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true*/
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }
    // install(Locations) http://ktor.io/servers/features/locations.html#artifact-info-34

    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
//            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    install(Metrics) {
        JmxReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build()
                .start()
        /*Slf4jReporter.forRegistry(registry)
                .outputTo(log)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build()
                .start(10, TimeUnit.SECONDS)*/
    }


    install(Routing) {
        schedule17(ScheduleInetRepoMockImpl())
        pAPI_ikt(IKTRepoImpl_asSample(log))
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }
    }
    //@see https://github.com/JetBrains/kotlinconf-app/blob/master/backend/src/org/jetbrains/kotlinconf/backend/Api.kt
    install(StatusPages) {
        exception<AuthenticationException> { cause ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<AuthorizationException> { cause ->
            call.respond(HttpStatusCode.Forbidden)
        }
        /*  exception<Throwable> { cause ->
              environment.log.error(cause)
              call.respond(HttpStatusCode.InternalServerError)
          }*/
    }
    /*  get("/json/jackson") {
          call.respond(mapOf("hello" to "world"))
      }*/
}

public suspend fun <R> PipelineContext<*, ApplicationCall>.errorAware(block: suspend () -> R): R? {

    return try {

        block()

    } catch (e: Exception) {

        call.respondText("""{"error":"$e"}""", ContentType.Application.Json  /*ContentType.parse("application/json")*/, HttpStatusCode.InternalServerError)

        null

    }

}
//private suspend fun ApplicationCall.respondSuccessJson(value: Boolean = true) = respond("""{"success": "$value"}""")

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

