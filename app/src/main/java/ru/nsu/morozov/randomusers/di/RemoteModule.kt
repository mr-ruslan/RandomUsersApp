package ru.nsu.morozov.randomusers.di

import dagger.Module
import dagger.Provides
import ru.nsu.morozov.randomusers.data.remote.repository.RandomUsersRepository
import ru.nsu.morozov.randomusers.data.remote.repository.RandomUsersRepositoryImpl

@Module
class RemoteModule {

    @Provides
    fun provideRandomUsersRepository(impl: RandomUsersRepositoryImpl): RandomUsersRepository = impl
}