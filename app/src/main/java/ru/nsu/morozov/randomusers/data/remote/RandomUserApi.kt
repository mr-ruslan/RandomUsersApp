package ru.nsu.morozov.randomusers.data.remote

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.nsu.morozov.randomusers.data.remote.dtos.GetRandomUsersResponse

interface RandomUserApi {
    @GET("api/")
    @Headers("Accept: application/json")
    suspend fun getRandomUsers(@Query("results") usersCount: Long): GetRandomUsersResponse
}