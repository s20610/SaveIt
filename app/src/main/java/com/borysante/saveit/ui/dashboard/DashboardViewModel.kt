package com.borysante.saveit.ui.dashboard

import androidx.lifecycle.viewModelScope
import com.borysante.saveit.ui.generic.events.EventBasedViewModel
import com.borysante.saveit.util.formatter.AmountFormatter.formatAmount
import com.borysante.saveit.util.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : EventBasedViewModel<DashboardEvent>() {
    private var state = MutableStateFlow(DashboardState.mockDashboardState)
    val dashboardState = state.asStateFlow()

    init {
        loadTransactions()
    }

    fun loadTransactions() {
        viewModelScope.launch {
            transactionRepository.getThisMonthTransactions()
                ?.collect { transactions ->
                    state.update {
                        it.copy(transactions = transactions)
                    }
                    countLastMonthExpenses()
                    countLastMonthEarnings()
                }
        }
    }

    private fun countLastMonthExpenses() {
        state.update { currentState ->
            currentState.copy(
                expenses = currentState.transactions.filter { transaction -> transaction.category.expense }
                    .sumOf { it.amount.toDouble() }.formatAmount()
            )
        }
    }

    private fun countLastMonthEarnings() {
        state.update { currentState ->
            currentState.copy(
                earnings = currentState.transactions.filter { transaction -> !transaction.category.expense }
                    .sumOf { it.amount.toDouble() }.formatAmount()
            )
        }
    }
}