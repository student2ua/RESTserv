package com.ua.ecwo.repos

import com.ua.ecwo.repos.DatabaseFactory.dbQuery
import com.ua.ecwo.repos.entity.IktElectronicCourses
import com.ua.ecwo.repos.entity.IktElectronicCoursesTable
import org.jetbrains.exposed.sql.*

class IKTRepoImpl_asSample : IKTRepo {
    suspend override fun create(entity: IktElectronicCourses) {
        update(entity)
    }

    override suspend fun update(entity: IktElectronicCourses): IktElectronicCourses? {
        dbQuery {
            IktElectronicCoursesTable.update({ IktElectronicCoursesTable.id eq entity.id }) {
                it[id] = entity.id
                it[name] = entity.name
                it[shortname] = entity.shortname
                it[departmentId] = entity.departmentId
            }

        }
        return findById(entity.id)
    }

    override suspend fun deleteById(id: Int): Boolean {
//       return IktElectronicCourses.findById(id)?.delete()?:
        return dbQuery {
            IktElectronicCoursesTable.deleteWhere { IktElectronicCoursesTable.id eq id } > 0
        }

    }

    override suspend fun findById(id: Int): IktElectronicCourses? = dbQuery {
        IktElectronicCoursesTable
                .select { (IktElectronicCoursesTable.id eq id) }
                .mapNotNull { toIKT(it) }
                .singleOrNull()
    }

    suspend override fun listAll(): List<IktElectronicCourses> = dbQuery {
        IktElectronicCoursesTable.selectAll().map { toIKT(it) }
    }

    private fun toIKT(row: ResultRow): IktElectronicCourses = IktElectronicCourses(
            id = row[IktElectronicCoursesTable.id],
            name = row[IktElectronicCoursesTable.name],
            shortname = row[IktElectronicCoursesTable.shortname],
            departmentId = row[IktElectronicCoursesTable.departmentId]
    )
}