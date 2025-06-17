package com.coutinho.estereofinance.ui.screens.categories

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coutinho.estereofinance.ui.theme.EstereoFinanceTheme // Importe o seu tema personalizado
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons

/**
 * Representa uma transação recente para a tela de detalhes da categoria.
 * @param icon O ícone associado à transação (por exemplo, Icons.Default.Home para aluguel).
 * @param description A descrição da transação (por exemplo, "Aluguel").
 * @param date A data da transação (por exemplo, "12/12/2024").
 * @param amount O valor da transação, formatado como string (por exemplo, "-R$1.000,00").
 */
data class RecentTransaction(
    val icon: FaIconType.SolidIcon,
    val description: String,
    val date: String,
    val amount: String
)

/**
 * Composable que representa a tela de Detalhes da Categoria.
 * Exibe informações detalhadas sobre uma categoria, incluindo seu saldo e transações recentes.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailsScreen(
    categoryName: String = "Household Expenses", // Nome da categoria padrão para preview
    numberOfTransactions: Int = 12,
    totalThisMonth: String = "R$1.250,00",
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onViewSubcategoriesClick: () -> Unit = {}
) {
    // Lista de transações recentes de exemplo
    val recentTransactions = listOf(
        RecentTransaction(
            FaIcons.Home
            , "Rent", "12/12/2024", "-R$1.000,00"),
        RecentTransaction(FaIcons.Anchor, "Utilities", "12/10/2024", "-R$150,00"),
        RecentTransaction(FaIcons.Wifi, "Internet", "12/05/2024", "-R$100,00")
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
                // Barra superior (Header)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = onBackClick) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                        FaIcon(
                            faIcon = FaIcons.ArrowLeft,
                            tint = TextDark,
                        )
                    }
                    Text(
                        text = "Category Details",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                    IconButton(onClick = onEditClick) {
//                        Icon(Icons.Default.Edit, contentDescription = "Editar")
                        FaIcon(
                            faIcon = FaIcons.Edit,
                            tint = TextDark,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Detalhes da Categoria
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ícone da Categoria (círculo)
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(CardBackground),
                        contentAlignment = Alignment.Center
                    ) {
                        FaIcon(
                            faIcon = FaIcons.Home, // Substitua pelo ícone da categoria
                            tint = TextDark,
                            modifier = Modifier.size(32.dp) // Tamanho do ícone
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = categoryName,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextDark
                        )
                        Text(
                            text = "$numberOfTransactions transactions",
                            fontSize = 16.sp,
                            color = TextLight
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Total deste mês
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total this month",
                        fontSize = 18.sp,
                        color = TextDark
                    )
                    Text(
                        text = totalThisMonth,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botão "View Subcategories"
                Button(
                    onClick = onViewSubcategoriesClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "View Subcategories",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Histórico Recente
                Text(
                    text = "Recent History",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Lista de Histórico Recente
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(recentTransactions) { transaction ->
                        RecentTransactionItem(transaction = transaction)
                    }
                }
            }
        }
    }
}

/**
 * Composable que representa um único item na lista de transações recentes.
 * Exibe o ícone, descrição, data e valor da transação.
 * @param transaction O objeto RecentTransaction a ser exibido.
 */
@Composable
fun RecentTransactionItem(transaction: RecentTransaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Círculo com ícone da transação
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(CardBackground),
                contentAlignment = Alignment.Center
            ) {
                FaIcon(
                    faIcon = transaction.icon,
                    tint = TextDark,
                    modifier = Modifier.size(24.dp) // Tamanho do ícone
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = transaction.description,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextDark
                )
                Text(
                    text = transaction.date,
                    fontSize = 14.sp,
                    color = TextLight
                )
            }
        }
        Text(
            text = transaction.amount,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red // Assumindo que todas as transações recentes são despesas aqui
        )
    }
}

/**
 * Função de preview para a tela de Detalhes da Categoria, útil para visualização no Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun PreviewCategoryDetailsScreen() {
    CategoryDetailsScreen()
}
