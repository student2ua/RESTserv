package com.ua.ecwo.repos.entity;

import org.jetbrains.exposed.sql.Table
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
data class IktElectronicCourses(
        val id: Int,
        val name: String,
        val shortname: String,
        val departmentId: Int)
/*class IktElectronicCourses(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<IktElectronicCourses>(IktElectronicCoursesTable)

    var name by IktElectronicCoursesTable.name
    var shortname by IktElectronicCoursesTable.shortname
    var departmentId by IktElectronicCoursesTable.departmentId
}*/

//object IktElectronicCoursesTable : IntIdTable("DCT_LOCAL.IKT_ELECTRONIC_COURSES") {
object IktElectronicCoursesTable : Table("DCT_LOCAL.IKT_ELECTRONIC_COURSES") {
    val id = integer("id")
    val name = varchar("NAME", 300)
    val shortname = varchar("SHORTNAME", 80);
    val departmentId = integer("DEPARTMENT_ID")
}


