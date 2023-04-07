package com.example.bluemooncaffe.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bluemooncaffe.R
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.data.drink1
import com.example.bluemooncaffe.viewModels.MainViewModel

@Composable
fun addToCartButton(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    drink: Product
){
    IconButton(onClick = {viewModel.addDrink(drink)}
    ) {
        Icon(imageVector = Icons.Filled.Add,
            tint = Color.Yellow,
            contentDescription = stringResource(id = R.string.addToCart),
            modifier= modifier
                .background(color = Color.Blue,
                    shape = CircleShape
                )

        )
    }
}


/*
@Preview
@Composable
fun addToCartButtonPreview(){
    addToCartButton(drink = drink1)
}*/
