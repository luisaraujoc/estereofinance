package com.coutinho.estereofinance.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val email: String,
    val passwordHash: String,  // Armazena o hash da senha
    val salt: String          // Armazena o salt usado no hash
) {
    companion object {
        fun create(name: String, email: String, password: String): User {
            val salt = SecurityUtils.generateSalt()
            val passwordHash = SecurityUtils.hashPassword(password, salt)
            return User(0, name, email, passwordHash, salt)
        }
    }

    fun verifyPassword(password: String): Boolean {
        return SecurityUtils.verifyPassword(password, salt, passwordHash)
    }
}