package com.coutinho.estereofinance.ui.screens.subcategories

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
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons

// Cores personalizadas com base nas imagens
val OrangePrimary = Color(0xFFF99D46)
val BackgroundLight = Color(0xFFFCF9F2)
val CardBackground = Color(0xFFF8EDDF)
val TextDark = Color(0xFF333333) // Cor para o texto principal, ajustada para contraste
val TextLight = Color(0xFFAAAAAA) // Cor para subtextos

/**
 * Representa uma subcategoria no aplicativo.
 * @param name O nome da subcategoria (por exemplo, "Aluguel").
 */
data class Subcategory(
    val name: String
)

/**
 * Composable que representa a tela de Subcategorias.
 * Exibe uma lista de subcategorias para uma categoria pai específica.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubcategoriesScreen(
    parentCategoryName: String = "Household Expenses", // Nome da categoria pai padrão para preview
    onBackClick: () -> Unit = {}
) {
    // Lista de subcategorias de exemplo para preencher a UI
    val subcategories = listOf(
        Subcategory("Rent"),
        Subcategory("Electricity"),
        Subcategory("Internet"),
        Subcategory("Groceries")
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
                    horizontalArrangement = Arrangement.Start // Alinhamento para o título e botão de voltar
                ) {
                    IconButton(onClick = onBackClick) {
                        FaIcon(faIcon = FaIcons.ArrowLeft, tint = TextDark)
                    }
                    Spacer(modifier = Modifier.width(16.dp)) // Espaço entre o ícone e o título
                    Text(
                        text = "Subcategories",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Categoria Pai
                Text(
                    text = "Parent Category: $parentCategoryName",
                    fontSize = 16.sp,
                    color = TextLight,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Lista de Subcategorias
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(subcategories) { subcategory ->
                        SubcategoryItem(subcategory = subcategory)
                    }
                }
            }
        }
    }
}

/**
 * Composable que representa um único item na lista de subcategorias.
 * Exibe o nome da subcategoria.
 * @param subcategory O objeto Subcategory a ser exibido.
 */
@Composable
fun SubcategoryItem(subcategory: Subcategory) {
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
            // Nome da subcategoria
            Text(
                text = subcategory.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = TextDark
            )
        }
    }
}

/**
 * Função de preview para a tela de Subcategorias, útil para visualização no Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun PreviewSubcategoriesScreen() {
    SubcategoriesScreen()
}
