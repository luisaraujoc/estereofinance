package com.coutinho.estereofinance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.coutinho.estereofinance.ui.screens.auth.LoginScreen
import com.coutinho.estereofinance.ui.screens.auth.RegisterScreen
import com.coutinho.estereofinance.ui.screens.home.HomeScreen
import com.coutinho.estereofinance.ui.screens.transactions.TransactionsScreen
import com.coutinho.estereofinance.ui.screens.categories.CategoriesScreen
import com.coutinho.estereofinance.ui.screens.categories.CategoryDetailsScreen
import com.coutinho.estereofinance.ui.screens.subcategories.SubcategoriesScreen

/**
 * Define as rotas para as diferentes telas do aplicativo.
 * Cada rota é um objeto que pode conter argumentos de navegação.
 */
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
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
                    // TODO: Implementar lógica de login e navegar para Home se bem-sucedido
                    navController.navigate(Screen.Home.route) {
                        // Limpa a pilha de volta para que o usuário não possa voltar para a tela de login
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
                    // TODO: Implementar lógica de registro e navegar para Home ou Login
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

        // Rota para a tela Home
        composable(Screen.Home.route) {
            HomeScreen()
            // As ações de navegação da Home Screen serão implementadas na navegação principal quando a BottomNavigationBar for adicionada
        }

        // Rota para a tela de Transações (anteriormente "History")
        composable(Screen.Transactions.route) {
            TransactionsScreen()
        }

        // Rota para a tela de Categorias
        composable(Screen.Categories.route) {
            CategoriesScreen()
            // TODO: Adicionar um `onCategoryClick` para navegar para CategoryDetails
            // Exemplo:
            // CategoriesScreen(onCategoryClick = { categoryName ->
            //     navController.navigate(Screen.CategoryDetails.createRoute(categoryName))
            // })
        }

        // Rota para a tela de Detalhes da Categoria com argumento
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

        // Rota para a tela de Subcategorias com argumento
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
