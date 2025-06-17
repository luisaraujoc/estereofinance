package com.coutinho.estereofinance.ui.screens.categories

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
 * Representa uma categoria no aplicativo.
 * @param name O nome da categoria (por exemplo, "Despesa Doméstica").
 */
data class Category(
    val name: String
)

/**
 * Composable que representa a tela de Categorias.
 * Exibe uma lista de categorias.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen() {
    // Lista de categorias de exemplo para preencher a UI
    val categories = listOf(
        Category("Household Expense"),
        Category("College"),
        Category("Work"),
        Category("Legal Entity Work"),
        Category("Leisure")
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
                // Título da tela "Categories"
                Text(
                    text = "Categories",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Lista de Categorias, exibindo cada item de categoria
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(categories) { category ->
                        CategoryItem(category = category)
                    }
                }
            }
        }
    }
}

/**
 * Composable que representa um único item na lista de categorias.
 * Exibe o nome da categoria.
 * @param category O objeto Category a ser exibido.
 */
@Composable
fun CategoryItem(category: Category) {
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
            // Nome da categoria
            Text(
                text = category.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = TextDark
            )
        }
    }
}

/**
 * Função de preview para a tela de Categorias, útil para visualização no Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun PreviewCategoriesScreen() {
    CategoriesScreen()
}
