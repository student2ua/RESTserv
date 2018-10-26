package com.ua.ecwo.repos

import com.ua.ecwo.repos.entity.IktElectronicCourses

interface IKTRepo : GenericRepository<IktElectronicCourses, Int> {
    override suspend fun create(entity: IktElectronicCourses): IktElectronicCourses
    override suspend fun modify(entity: IktElectronicCourses): IktElectronicCourses
    override suspend fun delete (entity: IktElectronicCourses)
    override suspend fun findById(id: Int): IktElectronicCourses
    override suspend fun findAll(): List<IktElectronicCourses>
    suspend fun deleteById(id: Int): Boolean

}
