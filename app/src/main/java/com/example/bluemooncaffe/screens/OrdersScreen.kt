package com.example.bluemooncaffe.screens

import com.example.bluemooncaffe.navigation.ScreenTab
import com.example.bluemooncaffe.viewModels.OrdersViewModel
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OrderScreen(
    navController: NavController,
    viewModel: OrdersViewModel,
    mainScreenTab: ScreenTab
){
    val orders= viewModel.getAllOrders().collectAsState(initial = listOf()).value
    Log.d("Order",orders.toString())
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Order Screen",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(
                        modifier = Modifier
                            .alignByBaseline()
                            .padding(start = 75.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = { TODO() },
                                modifier = Modifier.size(25.dp)
                            )
                            {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Home button"
                                )
                            }
                            Text(text = "All Orders", textAlign = TextAlign.Center)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                            .padding(start = 100.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = { TODO() },
                                modifier = Modifier
                                    .size(25.dp)
                            )
                            {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorites",
                                    modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
                                )
                            }
                            Text(text = "My Orders", textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    ) {
        if(mainScreenTab==ScreenTab.AllOrders){
            TODO()
        }
        else if(mainScreenTab==ScreenTab.MyOrders)
            TODO()
    }
}

/*
@Preview
@Composable
fun OrderScreenPreview(){
    OrderScreen()
}
*/
