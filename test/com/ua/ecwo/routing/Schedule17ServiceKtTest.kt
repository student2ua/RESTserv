package com.ua.ecwo.routing

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.ua.ecwo.model.IdNamed
import com.ua.ecwo.module
import io.ktor.config.MapApplicationConfig
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.contentType
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class Schedule17ServiceKtTest : AutoCloseKoinTest() {

    @Before
    fun setUp() {
//        startKoin(listOf(mainModule))
    }

    @Test
    fun testHelloRequest() = withTestApplication({

        (environment.config as MapApplicationConfig).apply {
            put("service.environment", "test")
            put("db_type", "h2")
        }
        module()
    }) {
        with(handleRequest(HttpMethod.Get, "/openAPI/rest/schedule/v170/faculties/")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertFalse(response.content.isNullOrBlank())
            var rezSet = jacksonObjectMapper().readValue<Set<IdNamed>>(content = response.content.toString())
            println("""contentType= ${response.contentType()}""")
            println(rezSet)
//            assertEquals("Hello Ktor & Koin !", response.content)
        }
        with(handleRequest(HttpMethod.Get, "/openAPI/rest/schedule/v170/faculties/1")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertFalse(response.content.isNullOrBlank())
            var rezSet = jacksonObjectMapper().readValue<IdNamed>(content = response.content.toString())
            println("""contentType= ${response.contentType()}""")
            println(rezSet)
        }
        with(handleRequest(HttpMethod.Get, "/openAPI/rest/schedule/v170/faculties/1/speciality")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertFalse(response.content.isNullOrBlank())
            var rezSet = jacksonObjectMapper().readValue<Set<IdNamed>>(content = response.content.toString())
            println("""contentType= ${response.contentType()}""")
            println(rezSet)
        }
        with(handleRequest(HttpMethod.Get, "/openAPI/rest/schedule/v170/faculties/1/speciality/1")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertFalse(response.content.isNullOrBlank())
            var rezSet = jacksonObjectMapper().readValue<IdNamed>(content = response.content.toString())
            println("""contentType= ${response.contentType()}""")
            println(rezSet)
        }
        with(handleRequest(HttpMethod.Get, "/openAPI/rest/schedule/v170/faculties/1/speciality/1/course/")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertFalse(response.content.isNullOrBlank())
            var rezSet = jacksonObjectMapper().readValue<Set<Int>>(content = response.content.toString())
            println("""contentType= ${response.contentType()}""")
            println(rezSet)
//            assertEquals("Hello Ktor & Koin !", response.content)
        }
        with(handleRequest(HttpMethod.Get,
                "/openAPI/rest/schedule/v170/faculties/1/speciality/1/course/1/studgroup/")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertFalse(response.content.isNullOrBlank())
            var rezSet = jacksonObjectMapper().readValue<Set<IdNamed>>(content = response.content.toString())
            println("""contentType= ${response.contentType()}""")
            println(rezSet)
//            assertEquals("Hello Ktor & Koin !", response.content)
        }

        with(handleRequest(HttpMethod.Get, "/index.html")) {
            assertFalse(requestHandled)
        }
    }

}