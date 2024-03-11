package ru.nsu.morozov.randomusers.data.remote.repository

import ru.nsu.morozov.randomusers.data.remote.UserConverter
import ru.nsu.morozov.randomusers.domain.entity.User
import ru.nsu.morozov.randomusers.domain.repository.UsersRepository

class UsersRepositoryImpl(private val randomUsersRepository: RandomUsersRepository) :
    UsersRepository {

    private val converter = UserConverter()
    override suspend fun getNewUsers(count: Long): List<User> =
        randomUsersRepository.getRandomUsers(count).map { converter.convert(it) }

    override suspend fun getLastUsers(): List<User> {
        TODO("Not yet implemented")
    }

}