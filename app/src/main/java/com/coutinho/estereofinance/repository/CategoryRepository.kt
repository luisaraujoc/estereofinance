package com.coutinho.estereofinance.repository


import com.coutinho.estereofinance.data.local.dao.CategoryDao
import com.coutinho.estereofinance.data.entity.Category
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val categoryDao: CategoryDao) {

    suspend fun addCategory(category: Category): Long = categoryDao.insert(category)

    suspend fun getCategoriesByUser(userId: Int): Flow<List<Category>> = categoryDao.getByUser(
        userId.toLong()
    )

    suspend fun deleteCategory(category: Category) = categoryDao.delete(category)
}
