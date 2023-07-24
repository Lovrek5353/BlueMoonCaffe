package com.example.bluemooncaffe.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bluemooncaffe.R
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.viewModels.CartViewModel

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    item: Product,
    onIconClick: () -> Unit = {},
    viewModel: CartViewModel
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(dimensionResource(id = R.dimen.drinkCardRound))
            )
            .size(
                width = dimensionResource(id = R.dimen.cartItemWidth),
                height = dimensionResource(id = R.dimen.cartItemHeight)
            )
            .background(color = Color.DarkGray)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = item.name,
                modifier = modifier
                    .width(100.dp)
                    .padding(start = 3.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Text(
                text = item.price.toString() + " €",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 3.dp)
            )
            IconButton(
                onClick = {
                    viewModel.removeDrink(item)
                    onIconClick()
                },
                modifier = Modifier.fillMaxHeight()
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = stringResource(id = R.string.removeItem),
                    tint = Color.White,
                    modifier = Modifier
                )
            }
        }
    }
}
