package com.coutinho.estereofinance.repository

import com.coutinho.estereofinance.data.entity.User
import com.coutinho.estereofinance.data.local.dao.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) : BaseRepository<User> {

    override suspend fun insert(user: User): Long {
        return userDao.insert(user)
    }

    override suspend fun update(user: User) {
        userDao.update(user)
    }

    override suspend fun delete(user: User) {
        userDao.delete(user)
    }

    suspend fun getUserById(id: Long): User? {
        return userDao.getUserById(id)
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    suspend fun createUser(name: String, email: String, password: String): User {
        val user = User.create(name, email, password)
        user.id = insert(user)
        return user
    }

    suspend fun authenticate(email: String, password: String): User? {
        val user = getUserByEmail(email)
        return if (user != null && user.verifyPassword(password)) user else null
    }
}