package com.coutinho.estereofinance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coutinho.estereofinance.data.entity.Category
import com.coutinho.estereofinance.repository.CategoryRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {

    private val _userId = MutableStateFlow<Long?>(null)

    val categories: StateFlow<List<Category>> = _userId
        .filterNotNull()
        .flatMapLatest { id -> repository.getCategoriesByUser(id.toInt()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun loadCategoriesForUser(userId: Long) {
        _userId.value = userId
    }

    fun addCategory(category: Category) {
        viewModelScope.launch {
            repository.addCategory(category)
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            repository.deleteCategory(category)
        }
    }
}
