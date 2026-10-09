package com.masterfixx.shebercrm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.masterfixx.shebercrm.ui.navigation.SheberNavHost
import com.masterfixx.shebercrm.ui.theme.SheberCRMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SheberCRMTheme {
                SheberNavHost()
            }
        }
    }
}