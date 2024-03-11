package ru.nsu.morozov.randomusers.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.nsu.morozov.randomusers.data.remote.dtos.GetRandomUsersResponse

interface RandomUserApi {
    @GET("/")
    suspend fun getRandomUsers(@Query("usersCount") usersCount: Long): GetRandomUsersResponse
}