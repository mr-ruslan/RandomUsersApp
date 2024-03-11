package ru.nsu.morozov.randomusers.presentation

import ru.nsu.morozov.randomusers.domain.entity.User

sealed interface ListState {
    data object Initial : ListState

    data object Loading : ListState

    data class Content(val items: List<User>) : ListState

    data class Error(val msg: String, val items: List<User>) : ListState
}