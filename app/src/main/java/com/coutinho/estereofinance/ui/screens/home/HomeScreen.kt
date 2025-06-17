package com.coutinho.estereofinance.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coutinho.estereofinance.ui.theme.EstereoFinanceTheme // Importe seu tema personalizado

// Cores personalizadas baseadas nas imagens
val OrangePrimary = Color(0xFFF99D46)
val BackgroundLight = Color(0xFFFCF9F2)
val CardBackground = Color(0xFFF8EDDF)
val TextDark = Color(0xFF333333) // Cor para o texto principal, ajustada para contraste
val TextLight = Color(0xFFAAAAAA) // Cor para subtextos

data class Transaction(
    val description: String,
    val category: String,
    val amount: String,
    val type: TransactionType // Adicione um tipo para indicar se é receita ou despesa
)

enum class TransactionType {
    INCOME, EXPENSE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val transactions = listOf(
        Transaction("Electricity bill", "Home expenses", "R$0,00", TransactionType.EXPENSE),
        Transaction("Internet", "Home expenses", "R$0,00", TransactionType.EXPENSE),
        Transaction("Water bill", "Home expenses", "R$0,00", TransactionType.EXPENSE),
        Transaction("Salary", "Income", "R$1.500,00", TransactionType.INCOME),
        Transaction("Groceries", "Food", "R$250,00", TransactionType.EXPENSE)
    )

    EstereoFinanceTheme {
        Scaffold(
            // bottomBar foi removido conforme solicitado
            containerColor = BackgroundLight // Cor de fundo da tela
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "Home",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Cartão de Saldo Geral
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    colors = CardDefaults.cardColors(containerColor = CardBackground)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "General Balance",
                            fontSize = 16.sp,
                            color = TextLight,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = "R$ 0,00",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextDark,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "income",
                                    fontSize = 14.sp,
                                    color = TextLight
                                )
                                Text(
                                    text = "R$ 0,00",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextDark
                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "expense",
                                    fontSize = 14.sp,
                                    color = TextLight
                                )
                                Text(
                                    text = "R$ 0,00",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextDark
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "History",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Lista de Histórico de Transações
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
            // Círculo de rádio simples
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(CardBackground) // Cor do círculo de rádio
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = transaction.description,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextDark
                )
                Text(
                    text = transaction.category,
                    fontSize = 14.sp,
                    color = TextLight
                )
            }
        }
        Text(
            text = "${if (transaction.type == TransactionType.EXPENSE) "-" else "+"}${transaction.amount}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (transaction.type == TransactionType.EXPENSE) Color.Red else Color.Green // Cores para despesas/receitas
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
