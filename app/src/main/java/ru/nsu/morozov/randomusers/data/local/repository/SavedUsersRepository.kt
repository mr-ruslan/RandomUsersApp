package ru.nsu.morozov.randomusers.data.local.repository

import ru.nsu.morozov.randomusers.data.local.model.UserModel

interface SavedUsersRepository {
    suspend fun getSavedUsers(): List<UserModel>

    suspend fun saveUsers(users: List<UserModel>) : List<UserModel>

    suspend fun replaceUsers(users: List<UserModel>) : List<UserModel>
}