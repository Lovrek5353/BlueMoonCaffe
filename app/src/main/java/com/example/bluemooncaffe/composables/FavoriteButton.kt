package com.example.bluemooncaffe.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bluemooncaffe.R
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.ui.theme.Red
import com.example.bluemooncaffe.ui.theme.Turquoise
import com.example.bluemooncaffe.viewModels.MainViewModel

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    item: Product,
    viewModel: MainViewModel
) {
    var isFavorite by remember {
        mutableStateOf(item.isFavorite)
    }
    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            item.isFavorite = !item.isFavorite
            if (isFavorite) {
                viewModel.addToFavorite(item)
            } else {
                viewModel.removeFromFavorite(item)
            }
        },
        modifier = modifier
            .background(Turquoise, shape = CircleShape)
            .padding(dimensionResource(id = R.dimen.padding5))
    ) {
        Icon(
            painter = painterResource(id = if (isFavorite) R.drawable.favorite else R.drawable.notfavorite),
            contentDescription = stringResource(id = R.string.favoriteButton),
            tint = Red
        )
    }
}
