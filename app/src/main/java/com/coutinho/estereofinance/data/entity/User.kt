package com.coutinho.estereofinance.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val email: String,
) {
}