package ru.nsu.morozov.randomusers.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import ru.nsu.morozov.randomusers.presentation.MainViewModel

@Module
interface ViewModelModule {

	@IntoMap
	@StringKey("MainViewModel")
	@Binds
	fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

}