package com.example.bluemooncaffe.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.bluemooncaffe.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.viewModels.MainViewModel

@Composable
fun FavoriteButton(
    modifier: Modifier= Modifier,
    item: Product,
    viewModel: MainViewModel
){
    var isFavorite by remember {
        mutableStateOf(item.isFavorite)
    }
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite=!isFavorite
            item.isFavorite=!item.isFavorite
            if(isFavorite){
                viewModel.addToFavorite(item)
            }
            else{
                viewModel.removeFromFavorite(item)
            }
        }
    ) {
        Icon(
            painter = painterResource(id = if (isFavorite) R.drawable.favorite else R.drawable.notfavorite),
            contentDescription = "Favorite Button",
        )
    }
}

/*
@Preview
@Composable
fun FavoriteButtonPreview(){
    FavoriteButton()
}*/
