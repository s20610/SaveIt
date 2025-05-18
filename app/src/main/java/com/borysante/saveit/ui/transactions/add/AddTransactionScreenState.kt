package com.borysante.saveit.ui.transactions.add

import com.borysante.saveit.data.dto.transactions.TransactionCategory
import java.time.LocalDate

data class AddTransactionScreenState(
    val title: String = "",
    val amount: String = "",
    val date: LocalDate? = null,
    val category: TransactionCategory? = null
)