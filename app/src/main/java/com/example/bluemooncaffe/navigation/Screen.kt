package com.example.bluemooncaffe.navigation

sealed class Screen(val route: String){
    object MainScreen: Screen("main")
    object CartScreen: Screen("cart")
    object LoginScreen: Screen("login")
    object OrdersScreen: Screen("orders")
    object StartScreen: Screen("start")
    object OfflineScreen: Screen("offline")
    object AllOrdersScreen: Screen("allOrders")
    object MyOrdersScreen: Screen("myOrders")
    object OrderTrackScreen: Screen("orderTrack")
    object SignInScreen: Screen("signIn")
}

open class ScreenTab() {
    object AllOrders: ScreenTab()
    object MyOrders: ScreenTab()
}
