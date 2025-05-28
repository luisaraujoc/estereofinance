package com.coutinho.estereofinance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coutinho.estereofinance.data.entity.Moviment
import com.coutinho.estereofinance.repository.MovimentRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.math.BigDecimal

class MovimentViewModel(private val repository: MovimentRepository) : ViewModel() {

    private val _userId = MutableStateFlow<Long?>(null)

    val moviments: StateFlow<List<Moviment>> = _userId
        .filterNotNull()
        .flatMapLatest { id -> repository.getMovimentsByUser(id.toInt()) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun loadMovimentsForUser(userId: Long) {
        _userId.value = userId
    }

    fun addMoviment(moviment: Moviment) {
        viewModelScope.launch {
            repository.addMoviment(moviment)
        }
    }

    fun deleteMoviment(moviment: Moviment) {
        viewModelScope.launch {
            repository.deleteMoviment(moviment)
        }
    }

    fun getTotalByCategories(userId: Int, categoryIds: List<Int>, onResult: (BigDecimal) -> Unit) {
        viewModelScope.launch {
            val total = repository.getTotalByCategories(userId, categoryIds)
            onResult(total)
        }
    }
}
