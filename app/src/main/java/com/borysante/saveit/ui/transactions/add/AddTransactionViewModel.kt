package com.borysante.saveit.ui.transactions.add

import androidx.lifecycle.viewModelScope
import com.borysante.saveit.data.dto.transactions.Transaction
import com.borysante.saveit.ui.generic.events.EventBasedViewModel
import com.borysante.saveit.util.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : EventBasedViewModel<AddTransactionEvent>() {
    private var state = MutableStateFlow(AddTransactionScreenState())
    val transactionState = state

    fun onTitleChanged(title: String) {
        state.update {
            it.copy(title = title)
        }
    }

    fun onAmountChanged(amount: String) {
        state.update {
            it.copy(amount = amount)
        }
    }

    fun onDateClicked(date: Date) {
        // Handle date selection logic if needed
    }

    fun onCategoryClicked() {
        // Handle category selection logic if needed
    }

    fun onAddClicked() {
        with(state.value){
            val transaction = Transaction(
                id = "", // Generate or assign ID as needed
                title = title,
                amount = amount.toFloat(),
                date = Date(), // Example: Use current date or selected date
                category = category // Example: Use selected category
            )

            viewModelScope.launch {
                try {
                    transactionRepository.addTransaction(transaction)
                    _events.emit(AddTransactionEvent.ShowMessage("Transaction added successfully"))
                    // Clear state after adding transaction if needed
                } catch (e: Exception) {
                    _events.emit(AddTransactionEvent.ShowMessage("Failed to add transaction: ${e.message}"))
                }
            }
        }
    }
}
