package com.coutinho.estereofinance.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "category",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId"])]
)
class Category (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    // type: expense or income...
    val type: String,
    val userId: Long
)