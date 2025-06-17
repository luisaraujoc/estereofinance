package com.coutinho.estereofinance.ui.screens.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coutinho.estereofinance.ui.theme.EstereoFinanceTheme // Importe o seu tema personalizado

// Cores personalizadas com base nas imagens
val OrangePrimary = Color(0xFFF99D46)
val BackgroundLight = Color(0xFFFCF9F2)
val CardBackground = Color(0xFFF8EDDF)
val TextDark = Color(0xFF333333) // Cor para o texto principal, ajustada para contraste
val TextLight = Color(0xFFAAAAAA) // Cor para subtextos

/**
 * Representa uma transação no aplicativo.
 * @param description A descrição da transação (por exemplo, "Conta de luz").
 * @param category A categoria à qual a transação pertence (por exemplo, "Despesas domésticas").
 * @param amount O valor da transação, formatado como string (por exemplo, "R$0,00").
 * @param type O tipo de transação, indicando se é uma receita ou despesa.
 */
data class Transaction(
    val description: String,
    val category: String,
    val amount: String,
    val type: TransactionType
)

/**
 * Define os tipos de transação possíveis.
 */
enum class TransactionType {
    INCOME, EXPENSE
}

/**
 * Composable que representa a tela de Transações (Histórico).
 * Exibe uma lista de transações com seu status (receita ou despesa).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen() {
    // Lista de transações de exemplo para preencher a UI
    val transactions = listOf(
        Transaction("Electricity bill", "Household expense", "R$0,00", TransactionType.EXPENSE),
        Transaction("Internet", "Legal entity work", "R$0,00", TransactionType.INCOME),
        Transaction("Water bill", "Household expense", "R$0,00", TransactionType.EXPENSE),
        Transaction("Internet", "Legal entity work", "R$0,00", TransactionType.INCOME),
        Transaction("Electricity bill", "Household expense", "R$0,00", TransactionType.EXPENSE),
        Transaction("Internet", "Legal entity work", "R$0,00", TransactionType.INCOME),
        Transaction("Water bill", "Household expense", "R$0,00", TransactionType.EXPENSE),
        Transaction("Internet", "Legal entity work", "R$0,00", TransactionType.INCOME)
    )

    EstereoFinanceTheme {
        Scaffold(
            containerColor = BackgroundLight // Define a cor de fundo da tela
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                // Título da tela "Transactions"
                Text(
                    text = "Transactions",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Lista de Histórico de Transações, exibindo cada item de transação
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(transactions) { transaction ->
                        TransactionItem(transaction = transaction)
                    }
                }
            }
        }
    }
}

/**
 * Composable que representa um único item na lista de transações.
 * Exibe a descrição, categoria e valor da transação, com cores indicando tipo (receita/despesa).
 * @param transaction O objeto Transaction a ser exibido.
 */
@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Um círculo simples que simula um rádio button
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(CardBackground) // Cor do círculo
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                // Descrição da transação
                Text(
                    text = transaction.description,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextDark
                )
                // Categoria da transação
                Text(
                    text = transaction.category,
                    fontSize = 14.sp,
                    color = TextLight
                )
            }
        }
        // Valor da transação com sinal e cor condicional
        Text(
            text = "${if (transaction.type == TransactionType.EXPENSE) "-" else ""}${transaction.amount}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (transaction.type == TransactionType.EXPENSE) Color.Red else Color.Green // Cores para despesas (vermelho) / receitas (verde)
        )
    }
}

/**
 * Função de preview para a tela de Transações, útil para visualização no Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun PreviewTransactionsScreen() {
    TransactionsScreen()
}
