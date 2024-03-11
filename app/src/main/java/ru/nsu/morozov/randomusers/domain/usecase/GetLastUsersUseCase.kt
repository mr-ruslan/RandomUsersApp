package ru.nsu.morozov.randomusers.domain.usecase

import ru.nsu.morozov.randomusers.domain.entity.User
import ru.nsu.morozov.randomusers.domain.repository.UsersRepository
import javax.inject.Inject

class GetLastUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(): List<User> = repository.getLastUsers()
}