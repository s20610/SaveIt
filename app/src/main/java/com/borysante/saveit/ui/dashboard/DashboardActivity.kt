package com.borysante.saveit.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.borysante.saveit.ui.generic.events.EventBasedActivity
import com.borysante.saveit.ui.transactions.add.AddTransactionActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : EventBasedActivity<DashboardViewModel, DashboardEvent>() {
    override val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
    }

    @Composable
    override fun SetupContent() {
        val state by viewModel.dashboardState.collectAsState()
        DashboardScreen(state = state, ::passEventToViewModel)
    }

    override fun handleEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.OnAddTransactionClicked -> {
                navigateToAddTransaction()
            }
        }
    }

    private fun navigateToAddTransaction() =
        startActivity(AddTransactionActivity.prepareIntent(this))

    companion object {
        fun prepareIntent(context: Context) = Intent(context, DashboardActivity::class.java)
    }
}
