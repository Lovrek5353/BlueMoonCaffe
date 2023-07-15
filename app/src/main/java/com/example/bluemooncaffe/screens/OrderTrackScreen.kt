package com.example.bluemooncaffe.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bluemooncaffe.R
import com.example.bluemooncaffe.composables.OrderTrack
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.navigation.Screen
import com.example.bluemooncaffe.viewModels.OrdersViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OrderTrackScreen(
    navController: NavController,
    viewModel: OrdersViewModel
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    var order by remember { mutableStateOf(Order()) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    LaunchedEffect(Unit) {
        viewModel.getSingleOrder().collect {
            order = it
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding10))
            .fillMaxWidth(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.MainScreen.route)
                        viewModel.resetOrder()
                }) {
                    Icon(
                        imageVector = Icons.Filled.List,
                        contentDescription = stringResource(id = R.string.main_screen)
                    )
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
                viewModel.getSingleOrder()
                swipeRefreshState.isRefreshing = false
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OrderTrack(order = order, viewModel = viewModel)
            }
        }
    }
}
