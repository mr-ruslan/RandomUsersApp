package ru.nsu.morozov.randomusers.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.nsu.morozov.randomusers.data.local.model.UserModel

@Dao
interface UsersDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addUser(user: UserModel)

	@Query("DELETE from users WHERE id = :userId")
	fun deleteUser(userId: Long)

	@Query("SELECT * from users")
	fun getUsers(): List<UserModel>
}