package ru.nsu.morozov.randomusers.di

import dagger.Module
import dagger.Provides
import ru.nsu.morozov.randomusers.data.local.UsersDatabase
import ru.nsu.morozov.randomusers.data.local.daos.UsersDao

@Module
class LocalModule {

    @Provides
    fun provideUsersDao(db: UsersDatabase): UsersDao = db.usersDao()
}