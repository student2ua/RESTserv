package com.ua.ecwo.routing

import com.ua.ecwo.errorAware
import com.ua.ecwo.repos.IKTRepo
import com.ua.ecwo.repos.entity.IktElectronicCourses
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Route.pAPI_ikt(repo: IKTRepo) {
    route("/papi/ikt/electroniccourses") {

        get("/") {
            errorAware {
                call.respond(repo.listAll())
            }
        }
        get("/{id}") {
            errorAware {
                val id = call.parameters["id"] ?: throw IllegalArgumentException("Parameter id not found")
                val ec = repo.findById(id.toInt())
                if (ec == null) call.respond(HttpStatusCode.NotFound)
                else call.respond(ec)
            }
        }
        post("/") {
            errorAware {
                val ec = call.receive<IktElectronicCourses>()
                call.respond(HttpStatusCode.Created, repo.create(ec))
            }
        }
        put("/") {
            errorAware {
                val ec = call.receive<IktElectronicCourses>()
                val upd = repo.update(ec)
                if (upd == null) call.respond(HttpStatusCode.NotFound)
                else call.respond(HttpStatusCode.OK, upd)
            }
        }
        delete("/{id}") {
            errorAware {
                val id = call.parameters["id"] ?: throw IllegalArgumentException("Parameter id not found")
                val removed = repo.deleteById(id.toInt())
                if (removed) call.respond(HttpStatusCode.OK)
                else call.respond(HttpStatusCode.NotFound)
            }
        }

    }
}