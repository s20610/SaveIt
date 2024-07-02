package com.borysante.saveit.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.borysante.saveit.ui.generic.events.EventBasedActivity
import com.borysante.saveit.ui.transactions.add.AddTransactionActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : EventBasedActivity<DashboardViewModel, DashboardEvent>(){
    override val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun SetupContent() {
        val state by viewModel.dashboardState.collectAsState()
        Scaffold(modifier = Modifier.fillMaxSize()) {
            DashboardScreen(state = state, ::passEventToViewModel)
        }
    }

    override fun handleEvent(event: DashboardEvent){
        when (event) {
            is DashboardEvent.OnAddTransactionClicked -> {
                startActivity(AddTransactionActivity.prepareIntent(this))
            }
        }
    }
}
