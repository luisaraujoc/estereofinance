package com.coutinho.estereofinance.data.local.dao

import androidx.room.*
import com.coutinho.estereofinance.data.entity.Subcategory

@Dao
interface SubcategoryDao {
    @Insert
    suspend fun insert(subcategory: Subcategory): Long

    @Update
    suspend fun update(subcategory: Subcategory)

    @Delete
    suspend fun delete(subcategory: Subcategory)

    @Query("SELECT * FROM subcategory WHERE userId = :userId")
    suspend fun getSubcategoriesByUser(userId: Long): List<Subcategory>

    @Query("SELECT * FROM subcategory WHERE categoryId = :categoryId AND userId = :userId")
    suspend fun getSubcategoriesByCategory(categoryId: Long, userId: Long): List<Subcategory>
}