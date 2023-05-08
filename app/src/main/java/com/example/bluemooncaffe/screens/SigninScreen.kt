package com.example.bluemooncaffe.screens

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignInScreen(

){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState=scaffoldState,
        topBar = {
            TopAppBar() {
                Text(text = "Please sign in into app",
                textAlign = TextAlign.Center)
            }
        },
        modifier = Modifier.fillMaxSize()
    ){
        val coroutineScope= rememberCoroutineScope()
        var usernameText by remember {
            mutableStateOf("")
        }
        var passwordText by remember {
            mutableStateOf("")
        }

        Column (
            Modifier.fillMaxSize()
        ) {
            //Username form
            Spacer(modifier = Modifier.height(30.dp))
            //Password box
        }

    }
}


@Preview
@Composable
fun SignInScreenPreview(){
    SignInScreen()
}