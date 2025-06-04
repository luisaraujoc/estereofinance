package com.coutinho.estereofinance.data.utils

import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

object SecurityUtils {
    private const val SALT_LENGTH = 32
    private const val HASH_ALGORITHM = "SHA-256"

    /**
     * Gera um salt aleatório para ser usado no hash da senha
     */
    fun generateSalt(): String {
        val random = SecureRandom()
        val salt = ByteArray(SALT_LENGTH)
        random.nextBytes(salt)
        return Base64.getEncoder().encodeToString(salt)
    }

    /**
     * Cria um hash seguro da senha usando salt
     */
    fun hashPassword(password: String, salt: String): String {
        val digest = MessageDigest.getInstance(HASH_ALGORITHM)
        digest.update(salt.toByteArray())
        val hashedBytes = digest.digest(password.toByteArray())
        return Base64.getEncoder().encodeToString(hashedBytes)
    }

    /**
     * Verifica se a senha fornecida corresponde ao hash armazenado
     */
    fun verifyPassword(password: String, salt: String, storedHash: String): Boolean {
        val newHash = hashPassword(password, salt)
        return newHash == storedHash
    }
}