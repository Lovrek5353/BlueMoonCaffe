package com.example.bluemooncaffe.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bluemooncaffe.navigation.Screen
import com.example.bluemooncaffe.viewModels.LoginViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StartScreen(
    navController: NavController,
    viewModel: LoginViewModel
){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState=scaffoldState,
        modifier = Modifier
            .padding(10.dp),
    ){
        LazyColumn(
            modifier= Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            item{
                Text(
                    text = "Welcome to the Bikini Bottom Caffe",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 50.dp)
                )
            }
            item {
                Button(onClick = { navController.navigate(Screen.LoginScreen.route) }) {
                    Text(text = "Customer")
                }
            }
            item{
                Button(onClick = { navController.navigate(Screen.OrdersScreen.route) }) {
                    Text(text = "Worker")
                }
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