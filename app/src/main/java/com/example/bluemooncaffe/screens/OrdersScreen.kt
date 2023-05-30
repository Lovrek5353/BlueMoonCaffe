package com.example.bluemooncaffe.screens


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bluemooncaffe.composables.OrderCard
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.navigation.ScreenTab
import com.example.bluemooncaffe.viewModels.OrdersViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OrderScreen(
    navController: NavController,
    viewModel: OrdersViewModel,
    mainScreenTab: ScreenTab
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    var refreshing by remember { mutableStateOf(false) }
    var orders by remember {
        mutableStateOf(listOf<Order>())
    }

    LaunchedEffect(Unit) {
        Log.d("Refresh", "Refresh")
        viewModel.getAllOrders().collect {
            orders = it
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Text(
                        text = "Order Screen",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    IconButton(onClick = {
                        TODO()
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
            Column {
                Text(text = "Primjer1")
                Button(onClick = { TODO() }) {
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
                            viewModel = viewModel,
                            OnClick = {
                                refreshing = true
                                viewModel.getAllOrders()
                                refreshing = false
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            )
        }
    }
}
