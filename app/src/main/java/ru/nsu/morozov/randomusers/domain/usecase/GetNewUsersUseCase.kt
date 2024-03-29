package ru.nsu.morozov.randomusers.domain.usecase

import ru.nsu.morozov.randomusers.domain.entity.User
import ru.nsu.morozov.randomusers.domain.repository.UsersRepository
import javax.inject.Inject

class GetNewUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(count: Long): List<User> = repository.getNewUsers(count)
}