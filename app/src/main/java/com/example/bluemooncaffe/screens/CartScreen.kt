package com.example.bluemooncaffe.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import com.example.bluemooncaffe.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.viewModels.CartViewModel

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel
){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    var order by remember { mutableStateOf(Order()) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    var refreshing by remember { mutableStateOf(false) }
    var total by remember { mutableStateOf(0.0) }

    LaunchedEffect(Unit) {
        viewModel.getOrder().collect {
            order = it
            total = totalPrice(order.products)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = Color.Green,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                    Icon(imageVector = Icons.Filled.List, contentDescription = stringResource(id = R.string.main_screen))
                }
                Text(
                    text = "Your cart",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 50.dp)

                )
            }
        }
    ) {
        SwipeRefresh(state = swipeRefreshState,
            onRefresh = {
                swipeRefreshState.isRefreshing = true
                viewModel.getOrder()
                total = totalPrice(order.products)
                swipeRefreshState.isRefreshing = false
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = R.string.yourOrder),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                item {
                    cartItemsList(
                        items = order.products,
                        viewModel = viewModel,
                        OnIconClick= {
                            refreshing = true
                            viewModel.getOrder()
                            total = totalPrice(order.products)
                            refreshing=false
                        },
                        refreshing=refreshing,
                        modifier = Modifier
                            .fillMaxWidth()
                            .scale(2f)
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = R.string.total) + ": " + total.toString() + " €",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                        fontSize = 30.sp
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            order.totalPrice=total
                            viewModel.addOrder(order) },
                    ) {
                        Text(text = stringResource(id = R.string.submit))
                    }
                }
            }
        }
    }
}

/*
@Preview
@Composable
fun CartScreenPreview() {
    CartScreen()
}
*/