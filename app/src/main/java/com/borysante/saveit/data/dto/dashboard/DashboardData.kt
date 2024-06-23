package com.borysante.saveit.data.dto.dashboard

import androidx.annotation.DrawableRes
import com.borysante.saveit.R

data class DashboardData(
    val userName: String,
    val earnings: String,
    val expenses: String,
    val thisWeekExpenses: List<Float>,
    val transactions: List<Transaction>
)

data class Transaction(
    val category: TransactionCategory,
    val title: String,
    val date: String,
    val amount: String,
)

enum class TransactionCategory(@DrawableRes val icon: Int) {
    FOOD(R.drawable.food_isometric_icon), GROCERY(R.drawable.grocery_isometric_icon), INCOME(R.drawable.cash_isometric_icon);

    fun getIconFromCategoryName(category: String) = entries.find { it.name == category }?.icon
}

val weekDays: List<String> = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

val mockDashboardData = DashboardData(
    userName = "John Doe",
    earnings = "$1000",
    expenses = "$500",
    thisWeekExpenses = listOf(0f, 70f, 20f, 0f, 0f, 0f, 0f),
    transactions = listOf(
        Transaction(TransactionCategory.FOOD, "Subway", "12/08/2021, Wednesday", "$20"),
        Transaction(TransactionCategory.GROCERY, "Grocery", "12/07/2021, Tuesday", "$70"),
        Transaction(TransactionCategory.INCOME, "Salary", "12/06/2021, Monday", "$1000"),
    )
)