package com.borysante.saveit.ui.transactions.add

import com.borysante.saveit.data.dto.transactions.TransactionCategory
import com.borysante.saveit.ui.generic.events.Event
import java.time.LocalDate
import java.util.Date

interface AddTransactionEvent : Event {
    data class ShowMessage(val message: String) : AddTransactionEvent
    data object OnAddClicked : AddTransactionEvent
    data object OnCancelClicked : AddTransactionEvent
    data class OnTitleChanged(val title: String) : AddTransactionEvent
    data class OnAmountChanged(val amount: String) : AddTransactionEvent
    data class OnDateChanged(val date: LocalDate) : AddTransactionEvent
    data class OnCategoryChanged(val category: TransactionCategory) : AddTransactionEvent
}