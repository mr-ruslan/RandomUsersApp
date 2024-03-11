package ru.nsu.morozov.randomusers.di

import dagger.Module
import dagger.Provides
import ru.nsu.morozov.randomusers.data.UsersRepositoryImpl
import ru.nsu.morozov.randomusers.domain.repository.UsersRepository
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

}