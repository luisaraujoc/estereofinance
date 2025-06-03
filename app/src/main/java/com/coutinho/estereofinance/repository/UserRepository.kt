package com.coutinho.estereofinance.repository

import com.coutinho.estereofinance.data.entity.User
import com.coutinho.estereofinance.data.entity.UserCredentials
import com.coutinho.estereofinance.data.local.dao.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao
){
    suspend fun addUser(user: User): Long = userDao.insert(user)

    suspend fun getUserById(userId: Long): User? = userDao.getById(userId)

    suspend fun getUserByEmail(email: String): User? = userDao.getByEmail(email)

    fun getAllUsers(): Flow<List<User>> = userDao.getAll()

    suspend fun updateUser(user: User) = userDao.update(user)

    suspend fun deleteUser(user: User) = userDao.delete(user)

    suspend fun register(user: User, password: String): Long {
        val userId = userDao.insert(user)
        val hashed = hashPassword(password)
        userDao.setPassword(userId, hashed)

        return userId
    }

    suspend fun login(email: String, password: String): UserCredentials? {
        val hashed = hashPassword(password)
        return userDao.login(email, hashed)
    }
}