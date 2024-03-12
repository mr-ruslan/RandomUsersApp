package ru.nsu.morozov.randomusers.data.tools

import ru.nsu.morozov.randomusers.data.remote.model.UserModel as RemoteModel
import ru.nsu.morozov.randomusers.data.local.model.UserModel as LocalModel
import ru.nsu.morozov.randomusers.domain.entity.User

class UserConverter {

    fun remote2domain(from: RemoteModel): User =
        with(from) {
            User(
                id = 0,
                firstName = name.first,
                lastName = name.last,
                nameTitle = name.title,
                gender = gender,
                country=location.country,
                state=location.state,
                city=location.city,
                street=location.street.name,
                house=location.street.number.toString(),
                postcode=location.postcode,
                latitude=location.coordinates.latitude,
                longitude=location.coordinates.longitude,
                age=birthday.age,
                birthDate=birthday.date,
                email=email,
                phone=phone,
                cell=cell,
                imagePreview=picture.medium,
                image=picture.large,
            )
        }
    fun local2domain(from: LocalModel): User =
        with(from) {
            User(
                id = id ?: 0,
                gender = gender,
                firstName = firstName,
                lastName = lastName,
                nameTitle = nameTitle,
                country=country,
                state=state,
                city=city,
                street=street,
                house=house,
                postcode=postcode,
                latitude=latitude,
                longitude=longitude,
                age=age,
                birthDate=birthDate,
                email=email,
                phone=phone,
                cell=cell,
                imagePreview=imagePreview,
                image=image
            )
        }
    fun remote2local(from: RemoteModel): LocalModel =
        with(from) {
            LocalModel(
                firstName = name.first,
                lastName = name.last,
                nameTitle = name.title,
                gender = gender,
                country=location.country,
                state=location.state,
                city=location.city,
                street=location.street.name,
                house=location.street.number.toString(),
                postcode=location.postcode,
                latitude=location.coordinates.latitude,
                longitude=location.coordinates.longitude,
                age=birthday.age,
                birthDate=birthday.date,
                email=email,
                phone=phone,
                cell=cell,
                imagePreview=picture.medium,
                image=picture.large,
            )
        }
}