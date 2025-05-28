package com.coutinho.estereofinance.data.local.dao

import androidx.room.*
import com.coutinho.estereofinance.data.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category): Long

    @Query("SELECT * FROM Category WHERE userId = :userId")
    suspend fun getByUserId(userId: Long): Flow<List<Category>>

    @Update
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)
}