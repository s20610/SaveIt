package com.borysante.saveit.ui.transactions.add

import com.borysante.saveit.data.dto.transactions.TransactionCategory

data class AddTransactionScreenState(
    val title: String = "",
    val amount: String = "",
    val date: String = "",
    val category: TransactionCategory = TransactionCategory.FOOD
)