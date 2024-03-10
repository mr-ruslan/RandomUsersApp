package ru.nsu.morozov.randomusers.domain.usecase

import ru.nsu.morozov.randomusers.domain.entity.User
import ru.nsu.morozov.randomusers.domain.repository.UsersRepository

class GetLastUsersUseCase(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(): List<User> = repository.getLastUsers()
}