package com.coutinho.estereofinance.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.coutinho.estereofinance.ui.screens.auth.LoginScreen
import com.coutinho.estereofinance.ui.screens.auth.RegisterScreen
import com.coutinho.estereofinance.ui.screens.home.HomeScreen
import com.coutinho.estereofinance.ui.screens.transactions.TransactionsScreen
import com.coutinho.estereofinance.ui.screens.categories.CategoriesScreen
import com.coutinho.estereofinance.ui.screens.categories.CategoryDetailsScreen
import com.coutinho.estereofinance.ui.screens.subcategories.SubcategoriesScreen
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIconType
import com.guru.fontawesomecomposelib.FaIcons

// Cores personalizadas (mantidas aqui para consistência com as telas)
val OrangePrimary = Color(0xFFF99D46)
val BackgroundLight = Color(0xFFFCF9F2)
val CardBackground = Color(0xFFF8EDDF)
val TextDark = Color(0xFF333333)
val TextLight = Color(0xFFAAAAAA)

/**
 * Define as rotas para as diferentes telas do aplicativo.
 * Cada rota é um objeto que pode conter argumentos de navegação.
 */
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object MainContent : Screen("main_content") // Nova rota para o conteúdo principal com a BottomBar

    // Rotas para as telas que terão a BottomNavigationBar
    object Home : Screen("home")
    object Transactions : Screen("transactions")
    object Categories : Screen("categories")

    // Rota para CategoryDetails com argumento 'categoryName'
    object CategoryDetails : Screen("category_details/{categoryName}") {
        fun createRoute(categoryName: String) = "category_details/$categoryName"
    }

    // Rota para Subcategories com argumento 'parentCategoryName'
    object Subcategories : Screen("subcategories/{parentCategoryName}") {
        fun createRoute(parentCategoryName: String) = "subcategories/$parentCategoryName"
    }
}

/**
 * Data class para definir os itens da Bottom Navigation Bar.
 */
data class BottomNavItem(
    val label: String,
    val icon: FaIconType.SolidIcon,
    val route: String
)

/**
 * Composable para a Bottom Navigation Bar.
 * @param navController O NavHostController para gerenciar a navegação dos itens da barra.
 */
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem("Home", FaIcons.Home, Screen.Home.route),
        BottomNavItem("Transactions", FaIcons.List, Screen.Transactions.route),
        BottomNavItem("Categories", FaIcons.Tag, Screen.Categories.route)
    )

    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.padding(top = 8.dp) // Adiciona um pequeno padding superior
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    // Evita recompor a tela se já estiver na rota selecionada
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // Pop up para o início do grafo para evitar múltiplas instâncias da mesma tela
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Evita múltiplas cópias da mesma tela quando selecionada
                            launchSingleTop = true
                            // Restaura o estado ao navegar de volta para a tela
                            restoreState = true
                        }
                    }
                },
                icon = {
                    FaIcon(
                        faIcon = item.icon,
                        tint = if (currentRoute == item.route) OrangePrimary else Color.Gray
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (currentRoute == item.route) OrangePrimary else Color.Gray
                    )
                }
            )
        }
    }
}

/**
 * Composable que envolve o conteúdo principal do aplicativo, incluindo a Bottom Navigation Bar.
 * @param mainNavController O NavHostController principal do aplicativo.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContentScreen(mainNavController: NavHostController) {
    val bottomNavController = rememberNavController() // NavController para a Bottom Bar

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = bottomNavController) },
        containerColor = BackgroundLight
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = Screen.Home.route, // A tela inicial dentro da Bottom Bar
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Transactions.route) {
                TransactionsScreen()
            }
            composable(Screen.Categories.route) {
                CategoriesScreen(
                    // Passa o NavController principal para navegação para CategoryDetails
                    /*onCategoryClick = { categoryName ->
                        mainNavController.navigate(Screen.CategoryDetails.createRoute(categoryName))
                    }*/
                )
            }
            // TODO: Adicionar a rota para "Reports" aqui quando a tela for criada
            composable("reports") {
                Text("Reports Screen Placeholder") // Placeholder para a tela de Reports
            }
        }
    }
}

/**
 * Composable principal que configura o grafo de navegação do aplicativo.
 * Gerencia a transição entre as diferentes telas.
 * @param navController O controlador de navegação para gerenciar o estado de navegação.
 */
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route // A tela inicial será a de Login
    ) {
        // Rota para a tela de Login
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = {
                    // TODO: Implementar lógica de login e navegar para MainContent se bem-sucedido
                    navController.navigate(Screen.MainContent.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }

        // Rota para a tela de Registro
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterClick = {
                    // TODO: Implementar lógica de registro e navegar para Login
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                }
            )
        }

        // Rota para o conteúdo principal com a BottomNavigationBar
        composable(Screen.MainContent.route) {
            MainContentScreen(mainNavController = navController) // Passa o navController principal para MainContentScreen
        }

        // Rota para a tela de Detalhes da Categoria com argumento (fora do grafo da BottomBar)
        composable(
            route = Screen.CategoryDetails.route,
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: "Detalhes da Categoria"
            CategoryDetailsScreen(
                categoryName = categoryName,
                onBackClick = { navController.popBackStack() },
                onViewSubcategoriesClick = {
                    navController.navigate(Screen.Subcategories.createRoute(categoryName))
                }
            )
        }

        // Rota para a tela de Subcategorias com argumento (fora do grafo da BottomBar)
        composable(
            route = Screen.Subcategories.route,
            arguments = listOf(navArgument("parentCategoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val parentCategoryName = backStackEntry.arguments?.getString("parentCategoryName") ?: "Subcategorias"
            SubcategoriesScreen(
                parentCategoryName = parentCategoryName,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
