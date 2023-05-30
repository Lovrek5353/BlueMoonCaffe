package com.example.bluemooncaffe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.bluemooncaffe.navigation.Navigation
import com.example.bluemooncaffe.navigation.Screen
import com.example.bluemooncaffe.ui.theme.BlueMoonCaffeTheme
import com.example.bluemooncaffe.utils.ConnectionState
import com.example.bluemooncaffe.utils.connectivityState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueMoonCaffeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val connection by connectivityState()
                    var isConnected = connection == ConnectionState.Available
                    if (isConnected) {
                        if (isFirstTime()) {
                            Navigation(startRoute = Screen.TermsScreen.route)
                        } else {
                            Navigation(startRoute = Screen.LoginScreen.route)
                        }
                    } else {
                        Navigation(startRoute = Screen.OfflineScreen.route)
                    }
                }
            }
        }
    }

    private fun isFirstTime(): Boolean {
        var pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        var ranBefore: Boolean = pref.getBoolean("RanBefore", false)
        if (!ranBefore) {
            // first time
            val editor = pref.edit()
            editor.putBoolean("RanBefore", true)
            editor.commit()
        }
        return !ranBefore
    }
}
