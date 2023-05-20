package com.example.bluemooncaffe.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.data.getDate
import com.example.bluemooncaffe.data.getStatus
import com.example.bluemooncaffe.viewModels.OrdersViewModel


@Composable
fun OrderCard(
    modifier: Modifier = Modifier,
    order: Order,
    OnClick: () -> Unit={},
    viewModel: OrdersViewModel
){
    Card(
        modifier = modifier.clip(RoundedCornerShape(30.dp)),
        backgroundColor = Color.Red
    )
    {
        LazyColumn(
            modifier = modifier
                .clip(RoundedCornerShape(30.dp))
                .height(200.dp)
        ) {
            item {
                Row {
                    Text(
                        text = "Order: "+order.id.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize= 20.sp,
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .weight(1.5f),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "Status: "+ getStatus(order.status),
                        fontWeight = FontWeight.Bold,
                        fontSize= 20.sp,
                        modifier = Modifier
                            .padding(end = 15.dp)
                    )
                }
            }
            val itemsCountMap=order.products.groupingBy { it }.eachCount()
            for((obj, count) in itemsCountMap){
                item{
                    Row(
                        modifier=Modifier.fillMaxWidth()
                    ){
                        Text(
                            text = obj.name,
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .weight(1.5f),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = "X"+count,
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .weight(1.5f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text =obj.price.toString() +" €",
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(end = 15.dp)
                        )
                    }
                }
            }

/*            for(product in order.products){
                item {
                    Row(
                        modifier= Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = product.name,
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .weight(1.5f),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = product.price.toString()+" €",
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(end = 15.dp)
                        )
                    }
                }
            }*/
            item{
                var time: String? = order.timestamp?.let { getDate(it) }
                Text("Time: "+ time)
            }
            item {
                Row(
                    modifier= Modifier.fillMaxWidth(),
                ){
                    Text(
                        text = "Total price: ",
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontSize= 20.sp,
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .weight(1.5f),
                    )
                    Text(
                        text = order.totalPrice.toString()+" €",
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,
                        fontSize= 20.sp,
                        modifier = Modifier.padding(end = 15.dp)
                    )
                }
            }
            item{
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    IconButton(onClick = {
                        viewModel.assingToMe(order.id)
                        OnClick()
                    }) {   //assign to me
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Assign to me"
                        )
                    }
                    IconButton(
                        onClick = { viewModel.changeToProcessing(order.id)
                                    OnClick()
                        }) {  //working on it
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = "Working on it")
                    }
                    IconButton(onClick = {
                        viewModel.changeToDelivered(order.id)
                        OnClick()
                    }) {  //delivered
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Delivered" )
                    }
                    IconButton(onClick = {
                        viewModel.changeToPaid(order.id)
                        OnClick()
                    }) {          //paid
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Paid" )
                    }
                    IconButton(onClick = {
                        viewModel.changeToCompleted(order.id)
                        OnClick()
                    }) {         //completed
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Completed")
                    }
                }
            }
        }

    }
}

/*
@Preview
@Composable
fun OrderScreenOrder(){
    OrderCard(order = order1)
}
*/