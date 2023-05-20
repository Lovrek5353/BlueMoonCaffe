package com.example.bluemooncaffe.screens

import com.example.bluemooncaffe.composables.drinksList
import com.example.bluemooncaffe.navigation.Screen
import com.example.bluemooncaffe.viewModels.MainViewModel
import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bluemooncaffe.utils.AppSettings.ordersEnabled

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel
){
    val juices= viewModel.getJuicess().collectAsState(initial = listOf()).value
    val beers=viewModel.getBeers().collectAsState(initial = listOf()).value
    val coffees=viewModel.getCoffees().collectAsState(initial = listOf()).value
    Log.d("Tag", "Jesmo li dobili sokice")
    val scaffoldState: ScaffoldState= rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    IconButton(onClick = {navController.navigate(Screen.LoginScreen.route) }) {
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
                            contentDescription = "Go to Cart")
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ){
        LazyColumn {
/*            item{
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Caffe",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            item{
                drinksList(items = drinks, viewModel = viewModel)
            }*/
            item{
                if(!ordersEnabled){
                    Text(
                        text = "You are in viewing mode and you can't order until you enter correct table number",
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text ="Please go back and enter valid table number",
                        textAlign = TextAlign.Center
                    )
                }
            }
            item {
                Button(onClick = { navController.navigate(Screen.FavoriteScreen.route) }) {
                    Text(text = "Favorite Items")
                }
            }
            item{
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Juice",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            item{
                drinksList(items = juices, viewModel = viewModel)
                Log.d("Tag", "Zasto nismo prikazali sokice?")
            }
            item{
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Beer",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            item{
                drinksList(items = beers, viewModel = viewModel)
            }
           item{
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Coffees",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            item{
                drinksList(items = coffees, viewModel = viewModel)
            }
        }
    }
}


/*@Preview
@Composable
fun MainScreenPreview(){
    MainScreen()
}*/