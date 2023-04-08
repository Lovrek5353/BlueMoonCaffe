package com.example.bluemooncaffe.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.bluemooncaffe.R

@Composable
fun OfflineScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier=modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_wifi) , 
            contentDescription = "No internet picture"
        )
        Text(text = "No signal",
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun OfflineScreenPreview(){
    OfflineScreen()
}