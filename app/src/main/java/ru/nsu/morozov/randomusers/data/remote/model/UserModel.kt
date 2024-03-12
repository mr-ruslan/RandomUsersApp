package ru.nsu.morozov.randomusers.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val name: UserName,
    @SerializedName("email") val email: String,
    @SerializedName("dob") val birthday: Timestamp,
    @SerializedName("phone") val phone: String,
    @SerializedName("cell") val cell: String,
    @SerializedName("picture") val picture: Picture,

)

data class UserName(
    @SerializedName("title") val title: String,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String,
)

data class Timestamp(
    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int,
)

data class Picture(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String
)