package com.example.bluemooncaffe.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bluemooncaffe.composables.drinksList
import com.example.bluemooncaffe.navigation.Screen
import com.example.bluemooncaffe.viewModels.MainViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    navController: NavController,
    modifier: Modifier,
    viewModel: MainViewModel
) {
    val favoriteItems = viewModel.getFavoriteItems().collectAsState(initial = listOf()).value
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { navController.navigate(Screen.StartScreen.route) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back to login screen"
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate(Screen.CartScreen.route) },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Go to Cart"
                        )
                    }
                }
            }
        }
    ) {
        drinksList(items = favoriteItems, viewModel = viewModel)
    }

}

/*
@Preview
@Composable
fun FavoriteScreenPreview(){
    FavoriteScreen()
}
*/
