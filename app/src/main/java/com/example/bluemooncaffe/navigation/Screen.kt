package com.example.bluemooncaffe.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("main")
    object CartScreen : Screen("cart")
    object LoginScreen : Screen("login")
    object OrdersScreen : Screen("orders")
    object StartScreen : Screen("start")
    object OfflineScreen : Screen("offline")
    object OrderTrackScreen : Screen("orderTrack")
    object SignInScreen : Screen("signIn")
    object FavoriteScreen : Screen("favorite")
    object CocktailScreen : Screen("cocktail")
    object MyOrdersScren : Screen("myorders")
    object TermsScreen : Screen("terms")
}

open class ScreenTab {
    object AllOrders : ScreenTab()
    object MyOrders : ScreenTab()
}
