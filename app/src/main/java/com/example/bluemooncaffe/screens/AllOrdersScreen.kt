package com.example.bluemooncaffe.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bluemooncaffe.composables.OrderCard
import com.example.bluemooncaffe.viewModels.OrdersViewModel
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllOrdersScreen(
    navController: NavController,
    viewModel: OrdersViewModel
){
    val scaffoldState = rememberScaffoldState()
    val orders = viewModel.getAllOrders().collectAsState(initial = listOf()).value
    Scaffold(
        scaffoldState=scaffoldState
    ) {
        Column(
            modifier= Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            for(order in orders){
                Spacer(modifier = Modifier.height(30.dp))
                OrderCard(order = order, viewModel = OrdersViewModel(get()))
            }
        }

    }
}

/*
@Preview
@Composable
fun AllOrdersScreenPreview(){
    AllOrdersScreen()
}*/
