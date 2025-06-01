package com.coutinho.estereofinance.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.*

@Entity(
    tableName = "moviment",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["userId"]),
        Index(value = ["categoryId"])
    ]
)
class Moviment (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val categoryId: Long,
    val title: String,
    val description: String,
    val price: BigDecimal,
    val date: Date = Date(),
)

// Estou tratando price com BigDecimal, double/float não vai ser recomendado para esse caso por causa de sua imprecisão.
// exemplo é simples, some na sua calculadora padrão do android 0.1 + 0.2 e veja o resultado.
// o resultado será 0.30000000000000004, e não 0.3 como esperado.
// é um erro pequeno, mas se tratando de um app pra cuidar do meu dinheiro, não quero esse tipo de erro.