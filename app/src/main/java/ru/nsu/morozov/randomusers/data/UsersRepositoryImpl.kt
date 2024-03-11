package ru.nsu.morozov.randomusers.data

import ru.nsu.morozov.randomusers.data.local.repository.SavedUsersRepository
import ru.nsu.morozov.randomusers.data.tools.UserConverter
import ru.nsu.morozov.randomusers.data.remote.repository.RandomUsersRepository
import ru.nsu.morozov.randomusers.domain.entity.User
import ru.nsu.morozov.randomusers.domain.repository.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val randomUsersRepository: RandomUsersRepository,
    private val savedUsersRepository: SavedUsersRepository
) :
    UsersRepository {

    private val converter = UserConverter()
    override suspend fun getNewUsers(count: Long): List<User> =
        savedUsersRepository
            .saveUsers(
                randomUsersRepository.getRandomUsers(count).map {
                    converter.remote2local(it)
                })
            .map { converter.local2domain(it) }

    override suspend fun getLastUsers(): List<User> {
        val result = savedUsersRepository.getSavedUsers()
        if (result.isEmpty()){
            return getNewUsers(10)
        }
        return result.map { converter.local2domain(it) }
    }

}