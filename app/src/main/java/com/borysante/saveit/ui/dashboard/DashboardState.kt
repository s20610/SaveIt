package com.borysante.saveit.ui.dashboard

import com.borysante.saveit.data.dto.transactions.Transaction
import com.borysante.saveit.data.dto.transactions.TransactionCategory
import java.time.LocalDate
import java.util.Date

data class DashboardState(
    val userName: String,
    val earnings: String,
    val expenses: String,
    val thisWeekExpenses: List<Float>,
    val transactions: List<Transaction>
) {
    companion object {
        val mockDashboardState = DashboardState(
            userName = "John Doe",
            earnings = "$1000",
            expenses = "$500",
            thisWeekExpenses = listOf(0f, 70f, 20f, 0f, 0f, 0f, 0f),
            transactions = listOf(
                Transaction(
                    id = "1",
                    title = "Grocery",
                    amount = 70f,
                    date = LocalDate.now(),
                    category = TransactionCategory.GROCERY
                ),
                Transaction(
                    id = "2",
                    title = "Food",
                    amount = 20f,
                    date = LocalDate.now(),
                    category = TransactionCategory.FOOD
                ),
                Transaction(
                    id = "3",
                    title = "Income",
                    amount = 100f,
                    date = LocalDate.now(),
                    category = TransactionCategory.INCOME
                )
            )
        )
    }
}

val weekDays: List<String> = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")