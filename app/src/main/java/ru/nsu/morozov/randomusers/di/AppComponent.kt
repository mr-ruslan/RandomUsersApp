package ru.nsu.morozov.randomusers.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import ru.nsu.morozov.randomusers.presentation.app.RandomUsersApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DomainModule::class,
        DataModule::class,
        RemoteModule::class,
        LocalModule::class,
        FragmentModule::class,
        ContextModule::class,
        ViewModelModule::class

    ]
)
interface AppComponent {

    fun inject(application: RandomUsersApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}