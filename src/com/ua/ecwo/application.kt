package com.ua.ecwo

import com.codahale.metrics.JmxReporter
import com.fasterxml.jackson.databind.SerializationFeature
import com.ua.ecwo.repos.MockScheduleInetRepoImpl
import com.ua.ecwo.repos.ScheduleInetRepo
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.metrics.Metrics
import io.ktor.pipeline.PipelineContext
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import java.util.concurrent.TimeUnit

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
    }

    install(DefaultHeaders) {
        header("X-Engine", "Ktor/Netty") // will send this header with each response
    }
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }
    // install(Locations) http://ktor.io/servers/features/locations.html#artifact-info-34
    /*  install(Webjars) { //import io.ktor.webjars.* нахуя оно нужно?
          path = "/webjars" //defaults to /webjars
          zone = ZoneId.systemDefault() //defaults to ZoneId.systemDefault()
      }*/

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
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

   /* val client = HttpClient() {
    }*/


 //@see https://github.com/JetBrains/kotlinconf-app/blob/master/backend/src/org/jetbrains/kotlinconf/backend/Api.kt
    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

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
        /**
         * http://dev:8080/openAPI/rest/faculties/6/specialities/2/sgroups.xml
         * список групп факультета/специальности, по умолчанию незакончившиеся на текущий учебный год
         * http://192.168.0.124:8080/schedule/faculties/1
         */
        get("/schedule/faculties/") {
            call.respond(MockScheduleInetRepoImpl().getFacultyAll())
        }
        get("/schedule/faculties/{fid}") {
            errorAware {
                val id = call.parameters["fid"] ?: throw IllegalArgumentException("Parameter id not found")
                call.respond(MockScheduleInetRepoImpl().getFaculty(id.toInt()))
            }
        }
        get("/schedule/faculties/{fid}/speciality/") {
            errorAware {
                val id = call.parameters["fid"] ?: throw IllegalArgumentException("Parameter id not found")
                call.respond(MockScheduleInetRepoImpl().getSpecialityAll(id.toInt()))
            }
        }
        get("/schedule/faculties/{fid}/speciality/{sid}") {
            errorAware {
                val id = call.parameters["fid"] ?: throw IllegalArgumentException("Parameter id not found")
                val sid = call.parameters["sid"] ?: throw IllegalArgumentException("Parameter id not found")
                call.respond(MockScheduleInetRepoImpl().getSpeciality(id.toInt(),sid.toInt()))
            }
        }
        get("/schedule/faculties/{fid}/speciality/{sid}/course/") {
            errorAware {
                val fid = call.parameters["fid"] ?: throw IllegalArgumentException("Parameter id not found")
                val sid = call.parameters["sid"] ?: throw IllegalArgumentException("Parameter id not found")
                call.respond(MockScheduleInetRepoImpl().getCourses(fid.toInt(),sid.toInt()))
            }
        }
        get("/schedule/faculties/{fid}/speciality/{sid}/course/{cours}/studgroup/") {
            errorAware {
                val fid = call.parameters["fid"] ?: throw IllegalArgumentException("Parameter faculties.id not found")
                val sid = call.parameters["sid"] ?: throw IllegalArgumentException("Parameter speciality.id not found")
                val course = call.parameters["cours"] ?: throw IllegalArgumentException("Parameter cours not found")
                call.respond(MockScheduleInetRepoImpl().getStudentGroups(fid.toInt(),sid.toInt(),course.toInt()))

            }
        }
        get("/schedule/faculties/{fid}/speciality/{sid}/course/{cours}/studgroup/{sg}") {
            errorAware {
                val fid = call.parameters["fid"] ?: throw IllegalArgumentException("Parameter faculties.id not found")
                val sid = call.parameters["sid"] ?: throw IllegalArgumentException("Parameter speciality.id not found")
                val course = call.parameters["cours"] ?: throw IllegalArgumentException("Parameter cours not found")
                val sg = call.parameters["sg"] ?: throw IllegalArgumentException("Parameter StudentGroup.id not found")
                call.respond(MockScheduleInetRepoImpl().getStudentGroups(fid.toInt(),sid.toInt(),course.toInt(),sg.toInt()))
            }
        }


        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }
        /*  get("/webjars") {
              call.respondText("<script src='/webjars/jquery/jquery.js'></script>", ContentType.Text.Html)
          }*/
    }

}

private suspend fun <R> PipelineContext<*, ApplicationCall>.errorAware(block: suspend () -> R): R? {

    return try {

        block()

    } catch (e: Exception) {

        call.respondText("""{"error":"$e"}""", ContentType.parse("application/json"), HttpStatusCode.InternalServerError)

        null

    }

}
//private suspend fun ApplicationCall.respondSuccessJson(value: Boolean = true) = respond("""{"success": "$value"}""")

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

