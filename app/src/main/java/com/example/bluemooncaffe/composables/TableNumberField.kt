package com.example.bluemooncaffe.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.bluemooncaffe.navigation.Screen
import com.example.bluemooncaffe.viewModels.LoginViewModel

@Composable
fun TableNumberTextField(
    navController: NavController,
    viewModel: LoginViewModel
){
    val coroutineScope= rememberCoroutineScope()
    var text by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = text ,
        onValueChange = {
            text=it
        },
        label = {
            Text(text = "Table number")
        },
        placeholder = {
            Text(text = "Enter table number ")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = false,
            keyboardType = KeyboardType.Number
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.LocationOn ,
                contentDescription = "TableNumberLeadingIcon"
            )
        },
        trailingIcon = {
            IconButton(onClick =
            {
                viewModel.setTableNumber(text.toInt())
                navController.navigate(Screen.MainScreen.route)
            }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "TableNumberTrailingIcon")
            }

        }

    )
}

/*
@Preview
@Composable
fun TableNumberTextFiledPreview(){
    TableNumberTextField()
}*/
