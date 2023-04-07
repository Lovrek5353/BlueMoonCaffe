package com.example.bluemooncaffe.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.viewModels.CartViewModel

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

/*
@Preview
@Composable
fun cartItemsListPreview(){
    cartItemsList(items = drinks)
}*/