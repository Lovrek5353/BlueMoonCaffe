package com.example.bluemooncaffe.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.bluemooncaffe.R
import com.example.bluemooncaffe.utils.AppSettings.ordersEnabled
import com.example.bluemooncaffe.data.Product
import com.example.bluemooncaffe.viewModels.MainViewModel

@Composable
fun drinkCard(
    modifier: Modifier = Modifier,
    item: Product,
    viewModel: MainViewModel
){
    Box(modifier = modifier
        .clip(RoundedCornerShape(dimensionResource(id = R.dimen.drinkCardRound)))
        .size(
            width = dimensionResource(id = R.dimen.drinkCardWidth),
            height = dimensionResource(id = R.dimen.drinkCardHeight)
        )
    ){
        if(item.imageLink!=""){
            Image(
                painter = rememberAsyncImagePainter(model = item.imageLink),
                contentDescription = stringResource(id = R.string.drinkImage),
                modifier= Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        else{
            Image(
                painter = painterResource(id = R.drawable.defaultdrink),
                contentDescription = stringResource(id = R.string.drinkImage),
                modifier= Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            if(ordersEnabled==true){
                addToCartButton(drink = item, viewModel = viewModel)
            }
            FavoriteButton(item = item, viewModel = viewModel)
        }
        Column(modifier = Modifier
            .padding(top=100.dp, start=3.dp)
        ) {
            Text(text = item.name,
                fontWeight = FontWeight.Bold,
                color= Color.Magenta
            )
            Text(text = item.price.toString()+ " €",
                fontWeight = FontWeight.Bold,
                color= Color.Magenta
            )
        }
    }
}