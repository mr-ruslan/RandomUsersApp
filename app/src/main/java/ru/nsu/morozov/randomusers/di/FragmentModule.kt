package ru.nsu.morozov.randomusers.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.nsu.morozov.randomusers.presentation.ui.UsersListFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeUsersListFragmentInjector(): UsersListFragment

}