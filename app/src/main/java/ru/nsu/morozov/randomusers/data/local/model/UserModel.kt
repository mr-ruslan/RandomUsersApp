package ru.nsu.morozov.randomusers.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserModel(
    val gender: String,
    val firstName: String,
    val lastName: String,
    val nameTitle: String,

    val country: String,
    val state: String,
    val city: String,
    val street: String,
    val house: String,
    val postcode: String,

    val latitude: Double,
    val longitude: Double,

    val age: Int,
    val birthDate: String,

    val email: String,
    val phone: String,
    val cell: String,

    val imagePreview: String,
    val image: String,
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}