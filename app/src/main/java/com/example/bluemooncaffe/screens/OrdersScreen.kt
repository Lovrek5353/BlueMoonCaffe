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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import com.example.bluemooncaffe.composables.OrderCard
import com.example.bluemooncaffe.composables.OrderList
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.data.totalPrice
import com.example.bluemooncaffe.navigation.Screen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OrderScreen(
    navController: NavController,
    viewModel: OrdersViewModel,
    mainScreenTab: ScreenTab
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    var refreshing by remember { mutableStateOf(false)}
    var orders by remember {
        mutableStateOf(listOf<Order>())
    }

    LaunchedEffect(Unit) {
        Log.d("Refresh", "Refresh")
        viewModel.getAllOrders().collect(){
            orders=it
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row() {
                    Text(
                        text = "Order Screen",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    IconButton(onClick = {
                        navController.navigate(Screen.MyOrdersScreen.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Assign to me",
                            tint = Color.White,
                        )
                    }
                }

            }
        },
        drawerGesturesEnabled = true,
        drawerContent = {
            Column() {
                Text(text = "Primjer1")
                Button(onClick = {viewModel.getUncompletedOrders()}) {
                    Text("Uncompleted")
                }
            }
        }
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                Log.d("Refresh", "Refresh")
                swipeRefreshState.isRefreshing = true
                viewModel.getAllOrders()
                swipeRefreshState.isRefreshing = false
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
                        OrderCard(
                            order = item,
                            viewModel =viewModel,
                            OnClick = {
                                refreshing=true
                                viewModel.getAllOrders()
                                refreshing=false
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            )
        }
    }
}
