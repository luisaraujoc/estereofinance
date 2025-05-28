package com.coutinho.estereofinance.repository

import com.coutinho.estereofinance.data.entity.Moviment
import com.coutinho.estereofinance.data.local.dao.MovimentDao
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

class MovimentRepository(private val movimentDao: MovimentDao) {

    suspend fun addMoviment(moviment: Moviment): Long = movimentDao.insert(moviment)

    fun getMovimentsByUser(userId: Int): Flow<List<Moviment>> = movimentDao.getAllByUserId(userId.toLong())

    fun getMovimentsByCategory(categoryId: Int): Flow<List<Moviment>> = movimentDao.getByCategory(categoryId.toLong())

    suspend fun getTotalByCategories(userId: Int, categoryIds: List<Int>): BigDecimal =
        movimentDao.getTotalByCategories(userId, categoryIds) ?: BigDecimal.ZERO

    suspend fun deleteMoviment(moviment: Moviment) = movimentDao.delete(moviment)
}
