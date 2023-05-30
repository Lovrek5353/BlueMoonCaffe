package com.example.bluemooncaffe.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bluemooncaffe.screens.*
import com.example.bluemooncaffe.viewModels.CartViewModel
import com.example.bluemooncaffe.viewModels.LoginViewModel
import com.example.bluemooncaffe.viewModels.MainViewModel
import com.example.bluemooncaffe.viewModels.OrdersViewModel
import org.koin.androidx.compose.get

@Composable
fun Navigation(startRoute: String) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startRoute
    ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(
                navController = navController,
                viewModel = MainViewModel(get())
            )

        }
        composable(route = Screen.CartScreen.route) {
            CartScreen(
                navController = navController,
                viewModel = CartViewModel(get())
            )
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(viewModel = LoginViewModel(get()), navController = navController)
        }
        composable(route = Screen.StartScreen.route) {
            StartScreen(navController = navController)
        }
        composable(route = Screen.OrdersScreen.route) {
            OrderScreen(
                navController = navController,
                viewModel = OrdersViewModel(get()),
                mainScreenTab = ScreenTab.AllOrders
            )
        }
        composable(Screen.OfflineScreen.route) {
            OfflineScreen()
        }
        composable(Screen.OrderTrackScreen.route) {
            OrderTrackScreen(navController = navController, viewModel = OrdersViewModel(get()))
        }
        composable(Screen.SignInScreen.route) {
            SignInScreen(navController = navController, viewModel = LoginViewModel(get()))
        }
        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(
                navController = navController,
                modifier = androidx.compose.ui.Modifier,
                viewModel = MainViewModel(
                    get()
                )
            )
        }
        composable(Screen.CocktailScreen.route) {
            CocktailScreen(navController = navController, viewModel = MainViewModel(get()))
        }
        composable(Screen.MyOrdersScren.route) {
            MyOrderScreen(navController = navController, viewModel = OrdersViewModel(get()))
        }
        composable(Screen.TermsScreen.route) {
            TermsScreen(navController = navController)
        }
    }
}