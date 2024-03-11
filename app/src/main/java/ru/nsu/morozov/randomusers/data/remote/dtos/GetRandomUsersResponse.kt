package ru.nsu.morozov.randomusers.data.remote.dtos

import com.google.gson.annotations.SerializedName
import ru.nsu.morozov.randomusers.data.remote.model.UserModel

data class GetRandomUsersResponse(
	@SerializedName("results") val results: List<UserModel>,
)

