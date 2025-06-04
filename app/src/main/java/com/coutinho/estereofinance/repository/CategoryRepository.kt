package com.coutinho.estereofinance.repository

import com.coutinho.estereofinance.data.entity.Category
import com.coutinho.estereofinance.data.local.dao.CategoryDao
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) : BaseRepository<Category> {

    override suspend fun insert(category: Category): Long {
        return categoryDao.insert(category)
    }

    override suspend fun update(category: Category) {
        categoryDao.update(category)
    }

    override suspend fun delete(category: Category) {
        categoryDao.delete(category)
    }

    suspend fun getCategoriesByUser(userId: Long): List<Category> {
        return categoryDao.getCategoriesByUser(userId)
    }

    suspend fun getCategoryById(id: Long, userId: Long): Category? {
        return categoryDao.getCategoryById(id, userId)
    }
}