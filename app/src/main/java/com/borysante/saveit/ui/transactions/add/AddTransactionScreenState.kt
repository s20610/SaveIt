package com.borysante.saveit.ui.transactions.add

import com.borysante.saveit.data.dto.transactions.TransactionCategory
import java.util.Date

data class AddTransactionScreenState(
    val title: String = "",
    val amount: String = "",
    val date: Date? = null,
    val category: TransactionCategory? = null
)