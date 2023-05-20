package com.example.bluemooncaffe.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bluemooncaffe.R
import com.example.bluemooncaffe.navigation.Screen
import com.example.bluemooncaffe.viewModels.LoginViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StartScreen(
    navController: NavController,
){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState=scaffoldState,
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = { navController.navigate(Screen.LoginScreen.route) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back to login screen"
                        )
                    }
                }
            }
        }
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Front Icon",
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = { navController.navigate(Screen.FavoriteScreen.route) }) {
                Text(text = "Favorite items  ")
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                Text("Menu items")
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = { /*TODO*/ }) {
                Text("Recommend a cocktail")

            }
        }

    }
}


/*
@Preview
@Composable
fun StartScreenPreview(){
    StartScreen()
}*/
