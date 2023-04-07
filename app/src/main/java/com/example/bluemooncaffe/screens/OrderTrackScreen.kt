package com.example.bluemooncaffe.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bluemooncaffe.composables.OrderTrack

@Composable
fun OrderTrackScreen(
    navController: NavController,
    viewModel: CartViewModel
){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    var order by remember { mutableStateOf(Order()) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    LaunchedEffect(Unit) {
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
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
            onRefresh = { TODO()
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    OrderTrack(order = order, viewModel = viewModel)
                }
            }
        }
    }
}