package ru.nsu.morozov.randomusers.di

import dagger.Module
import dagger.Provides
import ru.nsu.morozov.randomusers.domain.repository.UsersRepository
import ru.nsu.morozov.randomusers.domain.usecase.GetLastUsersUseCase
import ru.nsu.morozov.randomusers.domain.usecase.GetNewUsersUseCase

@Module
class DomainModule {

    @Provides
    fun provideGetLastUsersUseCase(usersRepository: UsersRepository): GetLastUsersUseCase =
        GetLastUsersUseCase(usersRepository)

    @Provides
    fun provideGetNewUsersUseCase(usersRepository: UsersRepository): GetNewUsersUseCase =
        GetNewUsersUseCase(usersRepository)
}