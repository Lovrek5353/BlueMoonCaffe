package com.example.bluemooncaffe.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.bluemooncaffe.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TermsScreen(
    navController: NavController
) {
    var isChecked by remember { mutableStateOf(false) }
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(
        modifier=Modifier
            .fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Terms and conditions",
                    textAlign = TextAlign.Center,
                    fontWeight= FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item{
                Text(
                    text = "Acceptance: By using the Order Management App, you agree to abide by these Terms and Conditions.\n" +
                            "\n" +
                            "Account Responsibility: You are responsible for maintaining the confidentiality of your account information.\n" +
                            "\n" +
                            "Appropriate Use: Use the App lawfully and refrain from distributing harmful, obscene, or illegal material.\n" +
                            "\n" +
                            "Order Management: The App facilitates order creation, tracking, and fulfillment. Accuracy of order information is your responsibility.\n" +
                            "\n" +
                            "Intellectual Property: All rights to the App belong to its owners. Do not copy, modify, or distribute the App without permission.\n" +
                            "\n" +
                            "Data Privacy: The App collects and processes user data according to the Privacy Policy.\n" +
                            "\n" +
                            "Third-Party Services: Integration with third-party services is subject to their own terms and conditions.\n" +
                            "\n" +
                            "Limitation of Liability: The owners are not liable for any damages resulting from the use or inability to use the App.\n" +
                            "\n" +
                            "Termination: The owners can terminate or suspend access to the App at any time.\n" +
                            "\n" +
                            "Modification of Terms: The owners may modify these Terms and Conditions at any time.\n" +
                            "\n" +
                            "Governing Law: These Terms and Conditions are governed by the laws of this jurisdiction.\n" +
                            "\n" +
                            "Entire Agreement: These Terms and Conditions constitute the entire agreement between you and the owner."
                )
            }
            item{
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    Text(text = "Accept: ")
                    Checkbox(checked = isChecked , onCheckedChange = {isChecked=it} )
                }
            }
            item {
                Button(onClick =
                {
                    if(isChecked==true){
                        navController.navigate(Screen.LoginScreen.route)
                    }
                }) {
                    Text(text = "Go to app")
                }
            }
        }
    }
}