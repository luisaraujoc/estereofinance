package com.coutinho.estereofinance.data.local.dao

import androidx.room.*
import com.coutinho.estereofinance.data.entity.Category

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: Category): Long

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT * FROM category WHERE userId = :userId")
    suspend fun getCategoriesByUser(userId: Long): List<Category>

    @Query("SELECT * FROM category WHERE id = :id AND userId = :userId")
    suspend fun getCategoryById(id: Long, userId: Long): Category?
}