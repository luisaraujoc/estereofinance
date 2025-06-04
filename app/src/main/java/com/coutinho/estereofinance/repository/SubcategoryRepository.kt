package com.coutinho.estereofinance.repository

import com.coutinho.estereofinance.data.entity.Subcategory
import com.coutinho.estereofinance.data.local.dao.SubcategoryDao
import javax.inject.Inject

class SubcategoryRepository @Inject constructor(
    private val subcategoryDao: SubcategoryDao
) : BaseRepository<Subcategory> {

    override suspend fun insert(subcategory: Subcategory): Long {
        return subcategoryDao.insert(subcategory)
    }

    override suspend fun update(subcategory: Subcategory) {
        subcategoryDao.update(subcategory)
    }

    override suspend fun delete(subcategory: Subcategory) {
        subcategoryDao.delete(subcategory)
    }

    suspend fun getSubcategoriesByUser(userId: Long): List<Subcategory> {
        return subcategoryDao.getSubcategoriesByUser(userId)
    }

    suspend fun getSubcategoriesByCategory(categoryId: Long, userId: Long): List<Subcategory> {
        return subcategoryDao.getSubcategoriesByCategory(categoryId, userId)
    }
}