package com.borysante.saveit.data.dto.transactions

import androidx.annotation.DrawableRes
import com.borysante.saveit.R
import java.util.Date

data class Transaction(
    val id: String? = null,
    val userId: String? = null,
    val title: String,
    val amount: Float,
    val date: Date,
    val category: TransactionCategory
)

enum class TransactionCategory(@DrawableRes val icon: Int, val categoryName: String) {
    FOOD(R.drawable.food_isometric_icon, "Food"), GROCERY(
        R.drawable.grocery_isometric_icon,
        "Grocery"
    ),
    INCOME(
        R.drawable.cash_isometric_icon,
        "Income"
    ),
    UTILITIES(R.drawable.chart_board_isometric_icon, "Utilities");
}