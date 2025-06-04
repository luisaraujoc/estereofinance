package com.coutinho.estereofinance.data.entity

data class LoggedUser(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
) {
}