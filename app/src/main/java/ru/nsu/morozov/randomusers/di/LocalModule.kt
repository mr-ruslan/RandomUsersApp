package ru.nsu.morozov.randomusers.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.nsu.morozov.randomusers.data.local.UsersDatabase
import ru.nsu.morozov.randomusers.data.local.daos.UsersDao
import ru.nsu.morozov.randomusers.data.local.repository.SavedUsersRepository
import ru.nsu.morozov.randomusers.data.local.repository.SavedUsersRepositoryImpl
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    fun provideUsersDao(db: UsersDatabase): UsersDao = db.usersDao()

    @Singleton
    @Provides
    fun provideSavedUsersRepository(impl: SavedUsersRepositoryImpl): SavedUsersRepository = impl

    @Singleton
    @Provides
    fun provideUsersDatabase(context: Context): UsersDatabase = UsersDatabase.getDatabase(context)

}