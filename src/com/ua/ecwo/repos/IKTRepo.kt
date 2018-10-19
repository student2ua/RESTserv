package com.ua.ecwo.repos

import com.ua.ecwo.repos.entity.IktElectronicCourses

interface IKTRepo {
    suspend fun create(entity: IktElectronicCourses)
    suspend fun update(entity: IktElectronicCourses): IktElectronicCourses?
    suspend fun deleteById(id: Int): Boolean
    suspend fun findById(id: Int): IktElectronicCourses?
    suspend fun listAll(): List<IktElectronicCourses>

}
