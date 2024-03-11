package ru.nsu.morozov.randomusers.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import ru.nsu.morozov.randomusers.data.remote.dtos.GetRandomUsersResponse

interface RandomUserApi {
    @GET("/?results={usersCount}")
    suspend fun getRandomUsers(@Path("usersCount") usersCount: Long): GetRandomUsersResponse
}