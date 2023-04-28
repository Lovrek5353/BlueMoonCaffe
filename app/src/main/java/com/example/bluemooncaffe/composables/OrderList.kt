package com.example.bluemooncaffe.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.viewModels.CartViewModel
import com.example.bluemooncaffe.viewModels.OrdersViewModel
import org.koin.androidx.compose.get
import androidx.compose.foundation.lazy.items

@Composable
fun OrderList(
    modifier: Modifier=Modifier,
    orders: List<Order>,
    viewModel: OrdersViewModel,
){
    Column() {
        for(order in orders){
            OrderCard(order = order, viewModel = viewModel)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

/*    LazyColumn {
        items(orders){
            OrderCard(order = it, viewModel = viewModel)
        }
    }*/
}

/*for(order in orders){
    OrderCard(order = order, viewModel = viewModel)
    Spacer(modifier = Modifier.height(10.dp))
}*/

/*
@Composable
fun cartItemsList(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel,
    refreshing: Boolean,
    OnIconClick: () -> Unit = {},
    items: List<Product>
){
    Column {
        for(item in items){
            CartItem(
                item = item,
                modifier = Modifier.padding(top = 5.dp, start = 5.dp, end = 5.dp),
                viewModel = CartViewModel(get()),
                onIconClick = OnIconClick
            )
        }
    }
}
*/
