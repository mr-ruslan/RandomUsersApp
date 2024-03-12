package ru.nsu.morozov.randomusers.domain.entity

data class User(
    val id: Long,
    val name: String,
    val phone: String,
    val age: Int,
    val email: String,
    val imagePreview: String,
    val image: String,
)
