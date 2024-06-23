package com.borysante.saveit.ui.dashboard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.borysante.saveit.ui.theme.FinancialGreen
import com.borysante.saveit.ui.theme.FinancialRed

@Composable
fun EarningExpensesCards(earnings: String, expenses: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
    ) {
        FinanceCard(title = "Earnings this month", value = earnings, color = FinancialGreen)
        FinanceCard(title = "Expenses this month", value = expenses, color = FinancialRed)
    }
}

@Composable
fun FinanceCard(title: String, value: String, color: Color) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp, pressedElevation = 5.dp
        ),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .size(width = 170.dp, height = 80.dp)
            .clickable { },
        colors = CardDefaults.cardColors().copy(containerColor = color)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = title, style = MaterialTheme.typography.labelMedium)
            Text(text = value, style = MaterialTheme.typography.titleLarge)
        }
    }
}