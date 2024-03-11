package ru.nsu.morozov.randomusers.data.remote

import ru.nsu.morozov.randomusers.data.remote.model.UserModel
import ru.nsu.morozov.randomusers.domain.entity.User

class UserConverter {

    fun convert(from: UserModel): User =
        with(from) {
            User(
                id = 0,
                name = name.title + name.first + name.last,
                phone = phone,
                age = birthday.age,
                email = email
            )
        }
}