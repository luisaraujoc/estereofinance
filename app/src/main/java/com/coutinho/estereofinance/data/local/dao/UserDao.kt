package com.coutinho.estereofinance.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.coutinho.estereofinance.data.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getById(id: Long): User?

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getByEmail(email: String): User?

    @Query ("SELECT * FROM user")
    suspend fun getAll(): Flow<List<User>>

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}