package com.borysante.saveit.data.dto.transactions

import androidx.annotation.DrawableRes
import com.borysante.saveit.R
import com.borysante.saveit.util.formatter.DateFormatter.toLocalDate
import java.io.Serializable
import java.time.LocalDate

open class Transaction(
    open val id: String? = null,
    open val title: String = "",
    open val amount: Float = 0.0f,
    val date: LocalDate = LocalDate.now(),
    open val category: TransactionCategory = TransactionCategory.FOOD
) : Serializable {
    companion object {
        fun fromServerTransaction(serverTransaction: ServerTransaction) = Transaction(
            id = serverTransaction.id,
            title = serverTransaction.title,
            amount = serverTransaction.amount,
            date = serverTransaction.serverTimestamp.toLocalDate(),
            category = serverTransaction.category
        )
    }
}

enum class TransactionCategory(
    @DrawableRes val icon: Int,
    val categoryName: String,
    val expense: Boolean = false
) {
    FOOD(
        R.drawable.food_isometric_icon,
        "Food",
        true
    ),
    GROCERY(
        R.drawable.grocery_isometric_icon,
        "Grocery",
        true
    ),
    INCOME(
        R.drawable.cash_isometric_icon,
        "Income"
    ),
    UTILITIES(
        R.drawable.chart_board_isometric_icon,
        "Utilities",
        true
    );
}