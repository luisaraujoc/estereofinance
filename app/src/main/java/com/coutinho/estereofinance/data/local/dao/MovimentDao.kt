package com.coutinho.estereofinance.data.local.dao

import androidx.room.*
import com.coutinho.estereofinance.data.entity.Moviment
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

@Dao
interface MovimentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(moviment: Moviment): Long

    @Query("SELECT * FROM Moviment WHERE userId = :userId ORDER BY date DESC")
    fun getAllByUserId(userId: Long): Flow<List<Moviment>>

    @Query("SELECT * FROM Moviment WHERE categoryId = :categoryId ORDER BY date DESC")
    fun getByCategory(categoryId: Long): Flow<List<Moviment>>

    @Query("SELECT SUM(price) FROM Moviment WHERE userId = :userId AND categoryId IN (:categoryIds)")
    suspend fun getTotalByCategories(
        userId: Int,
        categoryIds: List<Int>
    ): BigDecimal?

    @Query("""
        SELECT SUM(m.price) 
        FROM Moviment m
        INNER JOIN Category c ON m.categoryId = c.id
        WHERE m.userId = :userId AND c.type = :type
    """)
    suspend fun getTotalByTypeAndUserId(
        userId: Int,
        type: String // "income" ou "expense"
    ): BigDecimal?

    @Update
    suspend fun update(moviment: Moviment)

    @Delete
    suspend fun delete(moviment: Moviment)
}
