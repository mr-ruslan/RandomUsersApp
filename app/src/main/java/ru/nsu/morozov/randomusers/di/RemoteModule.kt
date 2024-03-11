package ru.nsu.morozov.randomusers.di

import dagger.Module
import dagger.Provides
import ru.nsu.morozov.randomusers.data.remote.repository.RandomUsersRepository
import ru.nsu.morozov.randomusers.data.remote.repository.RandomUsersRepositoryImpl
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideRandomUsersRepository(): RandomUsersRepository = RandomUsersRepositoryImpl()


}