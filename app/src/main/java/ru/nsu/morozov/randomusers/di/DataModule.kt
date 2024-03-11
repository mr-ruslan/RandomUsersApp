package ru.nsu.morozov.randomusers.di

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import ru.nsu.morozov.randomusers.data.UsersRepositoryImpl
import ru.nsu.morozov.randomusers.domain.repository.UsersRepository
import ru.nsu.morozov.randomusers.presentation.ViewModelFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

}