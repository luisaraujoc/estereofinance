package com.coutinho.estereofinance.data.local.dao

import androidx.room.*
import com.coutinho.estereofinance.data.entity.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Long): User?

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?
}