package ru.nsu.morozov.randomusers.domain.repository

import ru.nsu.morozov.randomusers.domain.entity.User

interface UsersRepository {
    suspend fun getNewUsers(count: Long): List<User>
    suspend fun getLastUsers(): List<User>
}