package com.borysante.saveit.ui.transactions.add

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.borysante.saveit.R
import com.borysante.saveit.data.dto.transactions.Transaction
import com.borysante.saveit.ui.generic.events.EventBasedViewModel
import com.borysante.saveit.util.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : EventBasedViewModel<AddTransactionEvent>() {

    private val _state = MutableStateFlow(AddTransactionScreenState())
    val transactionState = _state.asStateFlow()

    fun onEvent(event: AddTransactionEvent) {
        when (event) {
            is AddTransactionEvent.OnTitleChanged -> {
                _state.update { it.copy(title = event.title) }
            }

            is AddTransactionEvent.OnAmountChanged -> {
                _state.update { it.copy(amount = event.amount) }
            }

            is AddTransactionEvent.OnDateChanged -> {
                _state.update { it.copy(date = event.date) }
            }

            is AddTransactionEvent.OnCategoryChanged -> {
                _state.update { it.copy(category = event.category) }
            }

            is AddTransactionEvent.OnAddClicked -> {
                addTransaction()
            }

            is AddTransactionEvent.OnCancelClicked -> {
                launchEvent(AddTransactionEvent.OnCancelClicked)
            }

            else -> {
                launchEvent(AddTransactionEvent.ShowMessage("Unhandled event"))
            }
        }
    }

    private fun addTransaction() {
        with(_state.value) {
            if (title.isBlank() || amount.isBlank() || date == null || category == null) {
                launchEvent(AddTransactionEvent.ShowMessage(context.getString(R.string.please_fill_all_fields)))
                return
            }

            val transaction = Transaction(
                id = UUID.randomUUID().toString(),
                title = title,
                amount = prepareAmount(),
                date = date,
                category = category
            )

            viewModelScope.launch {
                try {
                    transactionRepository.addTransaction(transaction)
                    launchEvent(AddTransactionEvent.ShowMessage(context.getString(R.string.transaction_added_successfully)))
                    launchEvent(AddTransactionEvent.OnCancelClicked)
                } catch (e: Exception) {
                    Log.e("AddTransactionViewModel", "Error adding transaction", e)
                    launchEvent(AddTransactionEvent.ShowMessage(context.getString(R.string.error_adding_transaction)))
                }
            }
        }
    }

    private fun AddTransactionScreenState.prepareAmount(): Float = if (category?.expense == true) {
        -amount.toFloat()
    } else {
        amount.toFloat()
    }
}