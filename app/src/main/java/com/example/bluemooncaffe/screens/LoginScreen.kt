package com.example.bluemooncaffe.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bluemooncaffe.composables.TableNumberTextField
import com.example.bluemooncaffe.navigation.Screen
import com.example.bluemooncaffe.viewModels.LoginViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavController
){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            item {
                TableNumberTextField(navController,viewModel)
            }
/*            item{
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { *//*TODO*//* }) {
                    Text(text = "Enter")
                }
            }*/
            item{
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                    Text(text = "Proceed without ordering")
                }
            }
        }
    }
}


/*

@Preview
@Composable
fun LoginScreenPreview(){
    LoginScreen()
}
*/
