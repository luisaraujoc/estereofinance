package com.coutinho.estereofinance.data.local.dao

import androidx.room.*
import com.coutinho.estereofinance.data.entity.Trasaction
import java.util.*

@Dao
interface TransactionDao {
    @Insert
    suspend fun insert(transaction: Transaction): Long

    @Update
    suspend fun update(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("SELECT * FROM transaction WHERE userId = :userId")
    suspend fun getTransactionsByUser(userId: Long): List<Transaction>

    @Query("SELECT * FROM transaction WHERE id = :id AND userId = :userId")
    suspend fun getTransactionById(id: Long, userId: Long): Transaction?

    @Query("SELECT * FROM transaction WHERE userId = :userId AND date BETWEEN :startDate AND :endDate")
    suspend fun getTransactionsByDateRange(userId: Long, startDate: Date, endDate: Date): List<Transaction>

    @Query("SELECT * FROM transaction WHERE userId = :userId AND categoryId = :categoryId")
    suspend fun getTransactionsByCategory(userId: Long, categoryId: Long): List<Transaction>
}