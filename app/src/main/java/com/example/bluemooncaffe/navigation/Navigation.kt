package com.example.bluemooncaffe.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(startRoute: String){
    val navController= rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startRoute
    ){
        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController,
                viewModel = MainViewModel(get())
            )

        }
        composable(route= Screen.CartScreen.route){
            CartScreen(navController = navController,
                viewModel = CartViewModel(get())
            )
        }
        composable(route = Screen.LoginScreen.route){
            LoginScreen(viewModel = LoginViewModel(get()), navController = navController)
        }
        composable(route= Screen.StartScreen.route){
            StartScreen(navController = navController, viewModel = LoginViewModel(get()))
        }
        composable(route=Screen.OrdersScreen.route){
            OrderScreen(navController =navController,
                viewModel = OrdersViewModel(get()),
                mainScreenTab = ScreenTab.AllOrders
            )
        }
    }
}