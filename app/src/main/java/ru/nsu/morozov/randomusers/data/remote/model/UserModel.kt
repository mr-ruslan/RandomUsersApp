package ru.nsu.morozov.randomusers.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val name: UserName,
    @SerializedName("location") val location: Location,
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

data class Location(
    @SerializedName("street") val street: Street,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("postcode") val postcode: String,
    @SerializedName("coordinates") val coordinates: Coordinates,
)

data class Street(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String,
)

data class Coordinates(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)