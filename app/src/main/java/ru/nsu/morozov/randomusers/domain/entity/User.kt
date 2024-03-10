package ru.nsu.morozov.randomusers.domain.entity

data class User(
    val id: Int,
    val name: String,
    val phone: String,
    val age: Int,
    val email: String
)
