package com.borysante.saveit.ui.transactions.add

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.borysante.saveit.ui.generic.events.EventBasedActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionActivity : EventBasedActivity<AddTransactionViewModel, AddTransactionEvent>() {
    override val viewModel: AddTransactionViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun SetupContent() {
        val state by viewModel.transactionState.collectAsState()
        AddTransactionScreen(
            state = state,
            onEvent = viewModel::onEvent,
            snackbarHostState = snackbarHostState,
        )

        LaunchedEffect(Unit) {
            viewModel.events.collect { event ->
                handleEvent(event)
            }
        }
    }

    override fun handleEvent(event: AddTransactionEvent) {
        when (event) {
            is AddTransactionEvent.ShowMessage -> {
                showSnackbar(event.message)
            }

            is AddTransactionEvent.OnCancelClicked -> {
                finish()
            }

            else -> Unit
        }
    }

    companion object {
        fun prepareIntent(context: Context): Intent {
            return Intent(context, AddTransactionActivity::class.java)
        }
    }
}