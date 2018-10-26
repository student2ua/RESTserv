package com.ua.ecwo.repos

interface GenericRepository<E, I> {
    suspend fun create(entity: E): E

    suspend fun modify(entity: E): E

    suspend fun delete(entity: E)

    suspend fun findById(id: I): E

    suspend fun findAll(): List<E>
}