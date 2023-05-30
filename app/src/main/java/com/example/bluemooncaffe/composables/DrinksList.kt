package com.example.bluemooncaffe.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.bluemooncaffe.R
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.viewModels.MainViewModel
import org.koin.androidx.compose.get

@Composable
fun drinksList(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {},
    items: List<Product>,
    viewModel: MainViewModel
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding10))
    ) {
        items(items) {
            drinkCard(
                item = it,
                viewModel = MainViewModel(get()),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding5))
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