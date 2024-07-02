package com.borysante.saveit.ui.transactions.add

import com.borysante.saveit.ui.generic.events.Event
import java.util.Date

interface AddTransactionEvent : Event{
    data class ShowMessage(val message: String) : AddTransactionEvent
    data object OnAddClicked : AddTransactionEvent
    data object OnCancelClicked : AddTransactionEvent
    data class OnDateClicked(val date: Date) : AddTransactionEvent
    data object OnCategoryClicked : AddTransactionEvent
    data class OnTitleChanged(val title: String) : AddTransactionEvent
    data class OnAmountChanged(val amount: String) : AddTransactionEvent
}