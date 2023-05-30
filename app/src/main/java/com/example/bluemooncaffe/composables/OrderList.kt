package com.example.bluemooncaffe.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.bluemooncaffe.R
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.viewModels.OrdersViewModel

@Composable
fun OrderList(
    modifier: Modifier = Modifier,
    orders: List<Order>,
    viewModel: OrdersViewModel,
) {
    Column {
        for (order in orders) {
            OrderCard(order = order, viewModel = viewModel)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding10)))
        }
    }
}
