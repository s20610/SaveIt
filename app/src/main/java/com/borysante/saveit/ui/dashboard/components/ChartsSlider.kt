package com.borysante.saveit.ui.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.borysante.saveit.data.dto.dashboard.weekDays
import com.hd.charts.StackedBarChartView
import com.hd.charts.common.model.MultiChartDataSet
import com.hd.charts.style.ChartViewDefaults
import com.hd.charts.style.StackedBarChartDefaults

@Composable
fun ChartsSlider(expenses: List<Float>) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        ExpensesChart(expenses)
    }
}

@Composable
private fun ExpensesChart(expenses: List<Float>) {
    val items = weekDays.zip(expenses).map { (day, value) ->
        day to listOf(value)
    }

    val dataSet = MultiChartDataSet(
        items = items,
        prefix = "$",
        categories = listOf("Spent"),
        title = "Expenses this week"
    )

    StackedBarChartView(dataSet = dataSet, style = chartStyle())
}

@Composable
fun chartColor() = listOf(
    MaterialTheme.colorScheme.primary
)

@Composable
fun chartStyle() = StackedBarChartDefaults.style(
    barColors = chartColor(),
    chartViewStyle = ChartViewDefaults.style(
        outerPadding = 0.dp,
        innerPadding = 16.dp,
        shadow = 3.dp,
        width = 200.dp,
    )
)