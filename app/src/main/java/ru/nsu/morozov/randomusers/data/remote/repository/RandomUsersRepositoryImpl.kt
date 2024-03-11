package ru.nsu.morozov.randomusers.data.remote.repository

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nsu.morozov.randomusers.data.remote.RandomUserApi
import ru.nsu.morozov.randomusers.data.remote.model.UserModel
import java.util.concurrent.TimeUnit

class RandomUsersRepositoryImpl : RandomUsersRepository {
    private companion object {

        const val BASE_URL = "https://randomuser.me/"
        const val CONNECT_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
    }

    private val gson = GsonBuilder()
        .create()

    private val retrofit = Retrofit.Builder()
        .client(provideOkHttpClientWithProgress())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private fun provideOkHttpClientWithProgress(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()

    private val usersApi by lazy {
        retrofit.create(RandomUserApi::class.java)
    }

    override suspend fun getRandomUsers(count: Long): List<UserModel> =
        usersApi.getRandomUsers(count).results

}