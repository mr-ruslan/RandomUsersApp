package ru.nsu.morozov.randomusers.presentation

import ru.nsu.morozov.randomusers.domain.entity.User

sealed interface SelectedUserState {

    data object Absent : SelectedUserState

    data class Content(val user: User) : SelectedUserState

}