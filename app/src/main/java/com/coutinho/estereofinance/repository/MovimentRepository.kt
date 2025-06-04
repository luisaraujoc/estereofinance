package com.coutinho.estereofinance.repository

import com.coutinho.estereofinance.data.entity.Moviment
import com.coutinho.estereofinance.data.local.dao.MovimentDao
import java.util.*
import javax.inject.Inject

class MovimentRepository @Inject constructor(
    private val movimentDao: MovimentDao
) : BaseRepository<Moviment> {

    override suspend fun insert(moviment: Moviment): Long {
        return movimentDao.insert(moviment)
    }

    override suspend fun update(moviment: Moviment) {
        movimentDao.update(moviment)
    }

    override suspend fun delete(moviment: Moviment) {
        movimentDao.delete(moviment)
    }

    suspend fun getMovimentsByUser(userId: Long): List<Moviment> {
        return movimentDao.getMovimentsByUser(userId)
    }

    suspend fun getMovimentById(id: Long, userId: Long): Moviment? {
        return movimentDao.getMovimentById(id, userId)
    }

    suspend fun getMovimentsByDateRange(userId: Long, startDate: Date, endDate: Date): List<Moviment> {
        return movimentDao.getMovimentsByDateRange(userId, startDate, endDate)
    }

    suspend fun getMovimentsByCategory(userId: Long, categoryId: Long): List<Moviment> {
        return movimentDao.getMovimentsByCategory(userId, categoryId)
    }

    suspend fun getBalance(userId: Long): BigDecimal {
        val moviments = getMovimentsByUser(userId)
        var balance = BigDecimal.ZERO
        
        moviments.forEach { moviment ->
            balance = if (moviment.type == "income") {
                balance.add(moviment.price)
            } else {
                balance.subtract(moviment.price)
            }
        }
        
        return balance
    }
}