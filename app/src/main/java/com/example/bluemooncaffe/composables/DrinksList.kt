package com.example.bluemooncaffe.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.viewModels.MainViewModel
import androidx.compose.foundation.lazy.items

@Composable
fun drinksList(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit={},
    items: List<Product>,
    viewModel: MainViewModel
){
    LazyRow(modifier= Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(10.dp)
    ){
        items(items){
                drinkCard(item = it,
                    viewModel= MainViewModel(get()),
                    modifier = Modifier
                        .padding(5.dp)
                )
        }
    }
}

/*
@Preview
@Composable
fun drinksListPreview(){
    drinksList(items= drinks)
}*/