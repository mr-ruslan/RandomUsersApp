package ru.nsu.morozov.randomusers.data.tools

import ru.nsu.morozov.randomusers.data.remote.model.UserModel as RemoteModel
import ru.nsu.morozov.randomusers.data.local.model.UserModel as LocalModel
import ru.nsu.morozov.randomusers.domain.entity.User

class UserConverter {

    fun remote2domain(from: RemoteModel): User =
        with(from) {
            User(
                id = 0,
                name = name.title + name.first + name.last,
                phone = phone,
                age = birthday.age,
                email = email
            )
        }
    fun local2domain(from: LocalModel): User =
        with(from) {
            User(
                id = id,
                name = name,
                phone = phone,
                age = age,
                email = email
            )
        }
}