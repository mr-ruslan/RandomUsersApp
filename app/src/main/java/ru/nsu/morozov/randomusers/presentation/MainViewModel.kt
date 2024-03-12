package ru.nsu.morozov.randomusers.presentation

import android.util.Log
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

    private val _listState = MutableLiveData<ListState>(ListState.Initial)
    val listState: LiveData<ListState> = _listState

    private val _userState = MutableLiveData<SelectedUserState>(SelectedUserState.Absent)
    val userState: LiveData<SelectedUserState> = _userState


    fun loadData() {
        viewModelScope.launch {
            _listState.value = ListState.Loading
            try {
                val users = getLastUsersUseCase()
                _listState.value = ListState.Content(users)
            } catch (e: Exception) {
                _listState.value = ListState.Error(e.localizedMessage.orEmpty(), emptyList())
            }
        }
    }

    fun reloadData() {
        viewModelScope.launch {
            val previousUsers: List<User> = _listState.value.let {
                if (it is ListState.Content) it.items else if (it is ListState.Error) it.items else emptyList()

            }

            _listState.value = ListState.Loading

            try {
                val users = getNewUsersUseCase(USERS_COUNT)
                _listState.value = ListState.Content(users)
            } catch (e: Exception) {
                _listState.value = ListState.Error(e.localizedMessage.orEmpty(), previousUsers)
            }
        }
    }

    fun setSelectedUser(user: User) {
        _userState.value = SelectedUserState.Content(user)
    }


}