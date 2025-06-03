package com.coutinho.estereofinance.data.utils

data class PasswordRequirements(
    val length: Boolean,
    val hasUpper: Boolean,
    val hasLower: Boolean,
    val hasDigit: Boolean,
    val hasSpecial: Boolean
)

fun validatePassword(password: String): PasswordRequirements {
    return PasswordRequirements(
        length = password.length >= 8,
        hasUpper = password.any { it.isUpperCase() },
        hasLower = password.any { it.isLowerCase() },
        hasDigit = password.any { it.isDigit() },
        hasSpecial = password.any { !it.isLetterOrDigit() }
    )
}