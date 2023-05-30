package com.example.bluemooncaffe.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.data.getStatus
import com.example.bluemooncaffe.viewModels.OrdersViewModel

@Composable
fun OrderTrack(
    modifier: Modifier = Modifier,
    order: Order,
    viewModel: OrdersViewModel
) {
    Card(
        modifier = modifier.clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded30))),
        backgroundColor = Color.Red
    )
    {
        LazyColumn(
            modifier = modifier.clip(RoundedCornerShape(dimensionResource(id = R.dimen.rounded30)))
        ) {
            item {
                Row {
                    Text(
                        text = stringResource(id = R.string.order) + order.id.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.padding15))
                            .weight(1.5f),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = stringResource(id = R.string.status)+": " + getStatus(order.status),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(end = dimensionResource(id = R.dimen.padding15))
                    )
                }
            }
            for (product in order.products) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = product.name,
                            modifier = Modifier
                                .padding(start = dimensionResource(id = R.dimen.padding15))
                                .weight(1.5f),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = product.price.toString() + " €",
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding15))
                        )
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = stringResource(id = R.string.totalPrice),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(start = dimensionResource(id = R.dimen.padding15))
                            .weight(1.5f),
                    )
                    Text(
                        text = order.totalPrice.toString() + " €",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding15))
                    )
                }
            }
            item {
                Text(
                    text = stringResource(id = R.string.status)+": " + getStatus(order.status),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }
}

/*
@Preview
@Composable
fun OrderTrackPreview(){
    OrderTrack(order= order1)
}*/
