package com.example.bluemooncaffe.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.bluemooncaffe.R
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.ui.theme.Coral
import com.example.bluemooncaffe.ui.theme.Teal
import com.example.bluemooncaffe.viewModels.MainViewModel

@Composable
fun addToCartButton(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    drink: Product
) {
    IconButton(onClick = { viewModel.addDrink(drink) }
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            tint = Coral,
            contentDescription = stringResource(id = R.string.addToCart),
            modifier = modifier
                .background(
                    color = Teal,
                    shape = CircleShape
                )
        )
    }
}