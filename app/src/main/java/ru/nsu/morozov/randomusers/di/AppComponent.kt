package ru.nsu.morozov.randomusers.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.nsu.morozov.randomusers.MainActivity
import ru.nsu.morozov.randomusers.data.local.UsersDatabase

@Component(
	modules = [
		RemoteModule::class,
		DomainModule::class,
		LocalModule::class,
		DataModule::class,
	]
)
interface AppComponent {

	fun inject(activity: MainActivity)

	@Component.Factory
	interface ApplicationComponentFactory {

		fun create(
			@BindsInstance application: Application,
			@BindsInstance userDb: UsersDatabase,
		): AppComponent
	}
}