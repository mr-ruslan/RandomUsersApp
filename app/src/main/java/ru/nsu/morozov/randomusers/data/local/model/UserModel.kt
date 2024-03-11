package ru.nsu.morozov.randomusers.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserModel(
    val gender: String,
    val name: String,
    val email: String,
    val phone: String,
    val cell: String,
    val age: Int,
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}