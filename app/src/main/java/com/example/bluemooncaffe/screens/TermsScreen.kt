package com.example.bluemooncaffe.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bluemooncaffe.navigation.Screen
import java.nio.file.WatchEvent.Modifier
import java.util.concurrent.Flow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TermsScreen(
    navController: NavController
){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState=scaffoldState
    ) {
        LazyColumn(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        ){
            item{
                Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vehicula feugiat volutpat. Donec nec dui eget purus aliquet gravida. Nulla facilisi. Nulla diam arcu, condimentum nec facilisis volutpat, fringilla sed felis")
            }
            item{
                Text(text = "Morbi lectus diam, euismod at scelerisque a, finibus ac tortor. Donec non lorem ligula. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti. Aliquam viverra lacus ut imperdiet facilisis.")
            }
            item{
                Button(onClick = {navController.navigate(Screen.LoginScreen.route)}) {
                    Text(text = "Go to app")
                }
            }
        }
    }
}