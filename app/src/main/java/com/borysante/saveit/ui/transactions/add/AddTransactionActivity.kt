package com.borysante.saveit.ui.transactions.add

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.borysante.saveit.ui.generic.events.EventBasedActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionActivity : EventBasedActivity<AddTransactionViewModel, AddTransactionEvent>(){
    override val viewModel: AddTransactionViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun SetupContent() {
        val state by viewModel.transactionState.collectAsState()

        Scaffold(modifier = Modifier.fillMaxSize()) {
            AddTransactionScreen(
                state, ::passEventToViewModel
            )
        }
    }

    override fun handleEvent(event: AddTransactionEvent){
        when (event) {
            is AddTransactionEvent.ShowMessage -> {
                // Display a snackbar with the message
            }
            is AddTransactionEvent.OnAddClicked -> {
                viewModel.onAddClicked()
            }
            is AddTransactionEvent.OnCancelClicked -> {
                onBackPressedDispatcher.onBackPressed()
            }
            is AddTransactionEvent.OnDateClicked -> {
                // Handle date selection event
            }
            is AddTransactionEvent.OnCategoryClicked -> {
                // Handle category selection event
            }
            is AddTransactionEvent.OnTitleChanged -> {
                viewModel.onTitleChanged(event.title)
            }
            is AddTransactionEvent.OnAmountChanged -> {
                viewModel.onAmountChanged(event.amount)
            }
        }
    }

    companion object {
        fun prepareIntent(context: Context): Intent {
            return Intent(context, AddTransactionActivity::class.java)
        }
    }
}
