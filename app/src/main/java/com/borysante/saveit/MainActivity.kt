package com.borysante.saveit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.borysante.saveit.data.dto.dashboard.mockDashboardData
import com.borysante.saveit.ui.dashboard.DashboardScreen
import com.borysante.saveit.ui.theme.SaveItTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaveItTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    DashboardScreen(
                        mockDashboardData
                    )
                }
            }
        }
    }
}
