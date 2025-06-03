package com.coutinho.estereofinance.data.local.dao

import androidx.room.*
import com.coutinho.estereofinance.data.entity.User
import com.coutinho.estereofinance.data.entity.UserCredentials
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getById(id: Long): User?

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getByEmail(email: String): User?

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("UPDATE user SET senha = :hashed WHERE id = :userId")
    suspend fun setPassword(userId: Long, hashed: String)

    @Query("SELECT id, name, email FROM user WHERE email = :email AND senha = :hashed")
    suspend fun login(email: String, hashed: String): UserCredentials?
}
