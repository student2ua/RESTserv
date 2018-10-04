package com.ua.ecwo.routing

import com.ua.ecwo.errorAware
import com.ua.ecwo.repos.ScheduleInetRepo
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.schedule17(repo: ScheduleInetRepo) {
    route("/openAPI/rest/schedule/v170") {

        /**
         * список групп факультета/специальности, по умолчанию незакончившиеся на текущий учебный год
         * http://192.168.0.124:8080/openAPI/rest/faculties/1
         */
        get("/faculties/") {
            call.respond(repo.getFacultyAll())
        }
        get("/faculties/{fid}") {
            errorAware {
                val id = call.parameters["fid"] ?: throw IllegalArgumentException("Parameter id not found")
                call.respond(repo.getFaculty(id.toInt()))
            }
        }
        get("/faculties/{fid}/speciality/") {
            errorAware {
                val id = call.parameters["fid"] ?: throw IllegalArgumentException("Parameter id not found")
                call.respond(repo.getSpecialityAll(id.toInt()))
            }
        }
        get("/faculties/{fid}/speciality/{sid}") {
            errorAware {
                val id = call.parameters["fid"] ?: throw IllegalArgumentException("Parameter id not found")
                val sid = call.parameters["sid"] ?: throw IllegalArgumentException("Parameter id not found")
                call.respond(repo.getSpeciality(id.toInt(), sid.toInt()))
            }
        }
        get("/faculties/{fid}/speciality/{sid}/course/") {
            errorAware {
                val fid = call.parameters["fid"] ?: throw IllegalArgumentException("Parameter id not found")
                val sid = call.parameters["sid"] ?: throw IllegalArgumentException("Parameter id not found")
                call.respond(repo.getCourses(fid.toInt(), sid.toInt()))
            }
        }
        get("/faculties/{fid}/speciality/{sid}/course/{cours}/studgroup/") {
            errorAware {
                val fid = call.parameters["fid"] ?: throw IllegalArgumentException("Parameter faculties.id not found")
                val sid = call.parameters["sid"] ?: throw IllegalArgumentException("Parameter speciality.id not found")
                val course = call.parameters["cours"] ?: throw IllegalArgumentException("Parameter cours not found")
                call.respond(repo.getStudentGroups(fid.toInt(), sid.toInt(), course.toInt()))

            }
        }
        get("/faculties/{fid}/speciality/{sid}/course/{cours}/studgroup/{sg}") {
            errorAware {
                val fid = call.parameters["fid"] ?: throw IllegalArgumentException("Parameter faculties.id not found")
                val sid = call.parameters["sid"] ?: throw IllegalArgumentException("Parameter speciality.id not found")
                val course = call.parameters["cours"] ?: throw IllegalArgumentException("Parameter cours not found")
                val sg = call.parameters["sg"] ?: throw IllegalArgumentException("Parameter StudentGroup.id not found")
                call.respond(repo.getStudentGroups(fid.toInt(), sid.toInt(), course.toInt(), sg.toInt()))
            }
        }
    }
}