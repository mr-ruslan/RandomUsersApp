package ru.nsu.morozov.randomusers.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nsu.morozov.randomusers.domain.entity.User
import ru.nsu.morozov.randomusers.domain.usecase.GetLastUsersUseCase
import ru.nsu.morozov.randomusers.domain.usecase.GetNewUsersUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getNewUsersUseCase: GetNewUsersUseCase,
    private val getLastUsersUseCase: GetLastUsersUseCase
) : ViewModel() {
    companion object {
        const val USERS_COUNT = 10L
    }

    private val _state = MutableLiveData<ListState>(ListState.Initial)
    val state: LiveData<ListState> = _state

    fun loadData() {
        viewModelScope.launch {
            _state.value = ListState.Loading
            try {
                val users = getLastUsersUseCase()
                _state.value = ListState.Content(users)
            } catch (e: Exception) {
                _state.value = ListState.Error(e.localizedMessage.orEmpty(), emptyList())
            }
        }
    }

    fun reloadData() {
        viewModelScope.launch {
            var previousUsers: List<User> = _state.value.let {
                if (it is ListState.Content) it.items else emptyList()
            }

            _state.value = ListState.Loading

            try {
                val users = getNewUsersUseCase(USERS_COUNT)
                _state.value = ListState.Content(users)
            } catch (e: Exception) {
                _state.value = ListState.Error(e.localizedMessage.orEmpty(), previousUsers)
            }
        }
    }


}