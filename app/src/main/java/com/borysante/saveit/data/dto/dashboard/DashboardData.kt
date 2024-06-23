package com.borysante.saveit.data.dto.dashboard

import androidx.annotation.DrawableRes
import com.borysante.saveit.R

data class DashboardData(
    val userName: String,
    val earnings: String,
    val expenses: String,
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

val mockDashboardData = DashboardData(
    userName = "John Doe",
    earnings = "$1000",
    expenses = "$500",
    transactions = listOf(
        Transaction(TransactionCategory.FOOD, "Grocery", "12/12/2021", "$100"),
        Transaction(TransactionCategory.GROCERY, "Grocery", "12/12/2021", "$100"),
        Transaction(TransactionCategory.INCOME, "Grocery", "12/12/2021", "$100"),
    )
)