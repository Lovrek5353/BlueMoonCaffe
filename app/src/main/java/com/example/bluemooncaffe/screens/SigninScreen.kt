package com.example.bluemooncaffe.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bluemooncaffe.R
import com.example.bluemooncaffe.navigation.Screen
import com.example.bluemooncaffe.viewModels.LoginViewModel
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Please sign in into app",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        val coroutineScope = rememberCoroutineScope()
        var usernameText by remember {
            mutableStateOf("")
        }
        var passwordText by remember {
            mutableStateOf("")
        }
        var result by remember {
            mutableStateOf(0)
        }
        val loginResultState = remember { mutableStateOf<Result<AuthResult>?>(null) }
        //var temp: Flow<com.example.bluemooncaffe.screens.Result<AuthResult>> = flowOf<com.example.bluemooncaffe.screens.Result<AuthResult>>()
        var temp: Flow<Int> = flowOf<Int>()
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
            //Modifier.fillMaxSize()
        ) {

            //Add some big icon or fix existing one
            Image(
                painter = painterResource(id = R.drawable.fronticon),
                contentDescription = "Front Icon",
                modifier = Modifier
                    .size(100.dp)
            )
            //Username form
            OutlinedTextField(
                value = usernameText,
                onValueChange = {
                    usernameText = it
                },
                label = {
                    Text(text = "Username")
                },
                placeholder = {
                    Text(text = "Enter username")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "TableNumberLeadingIcon"
                    )
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            //Password box
            OutlinedTextField(
                value = passwordText,
                onValueChange = {
                    passwordText = it
                },
                label = {
                    Text("Password")
                },
                placeholder = {
                    Text(text = "Enter your password")
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "PasswordIcon"
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick =
                        {
                            coroutineScope.launch{
                                val result = viewModel.emailLogin(usernameText, passwordText).first()
                                loginResultState.value=result
                                if(loginResultState.value?.isSuccess == true){
                                    Log.d("Login", "Uspješna prijava")
                                    navController.navigate(Screen.OrdersScreen.route)
                                }
                                else{
                                    Log.d("Login", "Neuspješna prijava")
                                    //Toast.makeText(LocalContext.current, "Login failed", Toast.LENGTH_SHORT).show()
                                }

                            }
                        },
                        enabled = usernameText.isNotEmpty() && passwordText.isNotEmpty()
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "TableNumberTrailingIcon"
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = { /*TODO*/ }) {

            }
        }
    }

}

