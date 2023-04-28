package com.example.bluemooncaffe.screens

import com.example.bluemooncaffe.navigation.ScreenTab
import com.example.bluemooncaffe.viewModels.OrdersViewModel
import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.modifier.modifierLocalConsumer
import com.example.bluemooncaffe.composables.OrderCard
import com.example.bluemooncaffe.composables.OrderList
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.navigation.Screen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OrderScreen(
    navController: NavController,
    viewModel: OrdersViewModel,
    mainScreenTab: ScreenTab
) {
    //var orders = viewModel.getAllOrders().collectAsState(initial = listOf()).value
    var orders by remember { mutableStateOf(listOf<Order>()) }
    Log.d("Order", orders.toString())
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    LaunchedEffect(Unit){
        viewModel.getAllOrders().collect {
            orders=it
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
/*        modifier = Modifier
            .fillMaxSize(),*/
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
        }
/*        bottomBar = {
            BottomAppBar() {
                Text(text = "Neki bottom bar text")
            }
        }*/
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                swipeRefreshState.isRefreshing=true
                viewModel.getAllOrders()
                swipeRefreshState.isRefreshing=false
            }
        ) {
            LazyColumn(
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .wrapContentHeight(),
                content = {
                    items(orders) { item ->
                        OrderCard(order = item, viewModel = viewModel )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            )


/*               LazyColumn(
                    contentPadding=PaddingValues(10.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxHeight()
                        .wrapContentHeight(),
                    content = {
                        items(orders){ item ->
                            OrderCard(order = item, viewModel = viewModel )
                        }
                    }
                )*/
        }

    }
}


/*
@Preview
@Composable
fun OrderScreenPreview(){
    OrderScreen()
}
*/
