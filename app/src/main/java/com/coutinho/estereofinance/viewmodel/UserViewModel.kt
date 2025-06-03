package com.coutinho.estereofinance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coutinho.estereofinance.data.entity.User
import com.coutinho.estereofinance.data.entity.UserCredentials
import com.coutinho.estereofinance.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val users: StateFlow<List<User>> = repository.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    fun register(user: User, password: String, onSuccess: (Long) -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                val userId = repository.register(user, password)
                onSuccess(userId)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun login(email: String, password: String, onResult: (UserCredentials?) -> Unit) {
        viewModelScope.launch {
            val credentials = repository.login(email, password)
            onResult (credentials)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }
}
