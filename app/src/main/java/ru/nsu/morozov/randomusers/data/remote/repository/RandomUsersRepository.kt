package ru.nsu.morozov.randomusers.data.remote.repository

import ru.nsu.morozov.randomusers.data.remote.model.UserModel
import ru.nsu.morozov.randomusers.domain.entity.User

interface RandomUsersRepository {
    suspend fun getRandomUsers(count: Long): List<UserModel>
}