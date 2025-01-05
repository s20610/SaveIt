package com.borysante.saveit.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.borysante.saveit.ui.dashboard.DashboardState.Companion.mockDashboardState
import com.borysante.saveit.ui.dashboard.components.ChartsSlider
import com.borysante.saveit.ui.dashboard.components.EarningExpensesCards
import com.borysante.saveit.ui.dashboard.components.LastTransactionsList
import com.borysante.saveit.ui.theme.SaveItTheme

@Composable
fun DashboardScreen(state: DashboardState, onEvent: (DashboardEvent) -> Unit) {
    with(state) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                DashboardTopBar(userName, onEvent)
            },
            bottomBar = {
                DashboardBottomBar(onEvent)
            },
            floatingActionButtonPosition = FabPosition.Center,
            content = { innerPadding ->
                DashboardContent(innerPadding, state)
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopBar(userName: String, onEvent: (DashboardEvent) -> Unit) {
    TopAppBar(title = {
        Text(text = "Welcome, $userName")
    }, actions = {
        IconButton(onClick = { onEvent(DashboardEvent.OnNotificationsClicked) }) {
            Icon(Icons.Default.Notifications, contentDescription = "Notifications")
        }
        IconButton(onClick = { onEvent(DashboardEvent.OnProfileClicked) }) {
            Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
        }
    }, modifier = Modifier.padding(horizontal = 8.dp))
}

@Composable
fun DashboardBottomBar(onEvent: (DashboardEvent) -> Unit) {
    BottomAppBar(
        actions = {
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { /* Handle home click */ }) {
                Icon(
                    Icons.Outlined.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(25.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(
                onClick = { onEvent(DashboardEvent.OnAddTransactionClicked) },
            ) {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f, true))
            IconButton(onClick = { /* Handle settings click */ }) {
                Icon(
                    Icons.Outlined.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier.size(25.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f, true))
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun DashboardContent(innerPadding: PaddingValues, dashboardState: DashboardState) {
    with(dashboardState) {
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            EarningExpensesCards(earnings, expenses)
            ChartsSlider(expenses = thisWeekExpenses)
            LastTransactionsList(transactions)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    SaveItTheme {
        DashboardScreen(
            mockDashboardState, onEvent = { }
        )
    }
}
