package ru.nsu.morozov.randomusers.data.local.repository


import ru.nsu.morozov.randomusers.data.local.daos.UsersDao
import ru.nsu.morozov.randomusers.data.local.model.UserModel

class SavedUsersRepositoryImpl(private val usersDao: UsersDao) : SavedUsersRepository {
    override suspend fun getSavedUsers(): List<UserModel> =
        usersDao.getUsers()

    override suspend fun saveUsers(users: List<UserModel>) {
        users.forEach { usersDao.addUser(it) }
    }

}