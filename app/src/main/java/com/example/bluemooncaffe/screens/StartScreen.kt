package com.example.bluemooncaffe.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bluemooncaffe.R
import com.example.bluemooncaffe.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StartScreen(
    navController: NavController,
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navController.navigate(Screen.LoginScreen.route) },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back to login screen"
                        )
                    }
                    Text(
                        text = "Main menu",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
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
            Button(onClick = { navController.navigate(Screen.CocktailScreen.route) }) {
                Text("Recommend a cocktail")
            }
        }
    }
}


