package com.coutinho.estereofinance.data.local.dao

import androidx.room.*
import com.coutinho.estereofinance.data.entity.Moviment
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

@Dao
interface MovimentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(moviment: Moviment): Long

    @Query("SELECT * FROM moviments WHERE userid = :userId ORDER BY date DESC")
    fun getAllByUserId(userId: Long): Flow<List<Moviment>>

    @Query("SELECT * FROM moviments WHERE category = :category AND userid = :userId ORDER BY date DESC")
    suspend fun getByCategoryAndUserId(category: String, userId: Long): List<Moviment>

    @Query("SELECT SUM(price) FROM moviments WHERE userid = :userId and type = :type")
    suspend fun getTotalByTypeAndUserId(
        userId: Long,
        type: String
    ): BigDecimal

    @Update
    suspend fun update(moviment: Moviment)

    @Delete
    suspend fun delete(moviment: Moviment)
}