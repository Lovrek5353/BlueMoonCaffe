package com.example.bluemooncaffe.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.bluemooncaffe.R
import com.example.bluemooncaffe.data.Cocktail
import com.example.bluemooncaffe.data.Order
import com.example.bluemooncaffe.navigation.Screen
import com.example.bluemooncaffe.viewModels.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CocktailScreen(
    navController: NavController,
    viewModel: MainViewModel
){
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState= scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    IconButton(onClick = {navController.navigate(Screen.StartScreen.route) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back to login screen"
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate(Screen.MainScreen.route) },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Go to Main Screen")
                    }
                }
            }
        }
    ) {
        val cocktail=viewModel.getCocktail().collectAsState(initial = null).value
        //val cocktail=temp as Cocktail
        //Text(text = cocktail.toString())
        Log.d("Cocktail", cocktail.toString())

        if (cocktail != null) {
            for (t in cocktail){
                LazyColumn(
                    modifier=Modifier.fillMaxSize()
                ){
                    item{
                        Text(
                            text = t.name.toString(),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier=Modifier.height(20.dp))
                    }
                    item{
                        Column{
                                Text(text = "Ingridients: ")
                            for(ing in t!!.ingridients){
                                if(ing != null){
                                        Text("- "+ing.toString()+", ")
                                }
                            }
                        }
                    }
                    item{
                        Text(text = "Alcoholic: "+ t?.alkoholic)
                    }
                    item{
                        Text("Instructions: "+ (t?.intro))
                        Spacer(modifier=Modifier.height(20.dp))
                    }
                    item{
                        Log.d("Image", t.image.toString())
                        Image(
                            painter = rememberAsyncImagePainter(model = t.image.toString()),
                            contentDescription = stringResource(id = R.string.drinkImage),
                            modifier = Modifier
                                .width(500.dp)
                                .height(350.dp)
                        )
                    }
                }
            }
            /*LazyColumn(){
                item{
                    Text(text = cocktail?.first()?.name.toString())
                    Spacer(modifier=Modifier.height(20.dp))
                }
                item{
                    Image(
                        painter = rememberAsyncImagePainter(model = cocktail?.first()?.image),
                        contentDescription = stringResource(id = R.string.drinkImage),
                    )
                }
                item{
                    Text("Introduction: "+ (cocktail?.first()?.intro))
                }
                item{
                    Row{
                        Text(text = "Ingridients")
                        for(ing in cocktail?.first()!!.ingridients){
                            if(ing != null){
                                Text(ing.toString())
                            }
                        }
                    }
                }
                item{
                    Text(text = "Alcoholic: "+ cocktail?.first()?.alkoholic)
                }
            }*/

        }
    }
}

/*
@Preview
@Composable
fun CocktailScreenPreview(){
    CocktailScreen()
}*/
