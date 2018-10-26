package com.ua.ecwo.repos

import com.ua.ecwo.repos.DatabaseFactory.dbQuery
import com.ua.ecwo.repos.entity.IktElectronicCourses
import com.ua.ecwo.repos.entity.IktElectronicCoursesTable
import org.jetbrains.exposed.sql.*

class IKTRepoImpl_asSample(/*val log: org.slf4j.Logger*/) : IKTRepo {

    override suspend fun delete(entity: IktElectronicCourses) {
        deleteById(entity.id)
    }

    suspend override fun create(entity: IktElectronicCourses): IktElectronicCourses {
        dbQuery {
            IktElectronicCoursesTable.insert {
                it[id] = entity.id
                it[name] = entity.name
                it[shortname] = entity.shortname
                it[departmentId] = entity.departmentId
            }
        }
        return findById(entity.id)
    }

    override suspend fun modify(entity: IktElectronicCourses): IktElectronicCourses {
        return dbQuery {
            val update = IktElectronicCoursesTable.update({ IktElectronicCoursesTable.id eq entity.id }) {
                it[id] = entity.id
                it[name] = entity.name
                it[shortname] = entity.shortname
                it[departmentId] = entity.departmentId
            }
            IktElectronicCoursesTable
                    .select { (IktElectronicCoursesTable.id eq entity.id) }
                    .mapNotNull { toIKT(it) }
                    .single()
        }
//        return findById(entity.id)
    }

    override suspend fun deleteById(id: Int): Boolean {
//       return IktElectronicCourses.findById(id)?.delete()?:
        return dbQuery {
            IktElectronicCoursesTable.deleteWhere { IktElectronicCoursesTable.id eq id } > 0
        }

    }

    override suspend fun findById(id: Int): IktElectronicCourses = dbQuery {
        IktElectronicCoursesTable
                .select { (IktElectronicCoursesTable.id eq id) }
                .mapNotNull { toIKT(it) }
                .single()
    }

    suspend override fun findAll(): List<IktElectronicCourses> = dbQuery {
        IktElectronicCoursesTable.selectAll().map { toIKT(it) }
    }

    private fun toIKT(row: ResultRow): IktElectronicCourses = IktElectronicCourses(
            id = row[IktElectronicCoursesTable.id],
            name = row[IktElectronicCoursesTable.name],
            shortname = row[IktElectronicCoursesTable.shortname],
            departmentId = row[IktElectronicCoursesTable.departmentId]
    )
}

fun ResultRow.toIkt() = IktElectronicCourses(
        id = this[IktElectronicCoursesTable.id],
        name = this[IktElectronicCoursesTable.name],
        shortname = this[IktElectronicCoursesTable.shortname],
        departmentId = this[IktElectronicCoursesTable.departmentId]
)