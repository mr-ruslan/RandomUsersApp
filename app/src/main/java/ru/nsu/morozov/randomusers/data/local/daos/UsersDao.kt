package ru.nsu.morozov.randomusers.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.nsu.morozov.randomusers.data.local.model.UserModel

@Dao
interface UsersDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addUser(user: UserModel) : Long

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun addUsers(users: List<UserModel>) : List<Long>

	@Query("DELETE from users WHERE id = :userId")
	suspend fun deleteUser(userId: Long)

	@Query("SELECT * from users")
	suspend fun getUsers(): List<UserModel>
}