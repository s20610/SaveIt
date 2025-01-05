package com.borysante.saveit.ui.transactions.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.borysante.saveit.ui.components.ThemedText
import java.util.Date

@Composable
fun AddTransactionScreen(
    state: AddTransactionScreenState,
    onEvent: (AddTransactionEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AddTransactionTopBar(onEvent)
        },
        content = { innerPadding ->
            AddTransactionContent(state, onEvent, innerPadding)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionTopBar(onEvent: (AddTransactionEvent) -> Unit) {
    TopAppBar(title = {
        Text(text = "Add transaction")
    }, navigationIcon = {
        IconButton(onClick = { onEvent(AddTransactionEvent.OnCancelClicked) }) {
            Icon(imageVector = Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Back")
        }
    }, modifier = Modifier.padding(horizontal = 8.dp))
}

@Composable
fun AddTransactionContent(
    state: AddTransactionScreenState,
    onEvent: (AddTransactionEvent) -> Unit,
    innerPaddingValues: PaddingValues
) {
    with(state) {
        Column(
            modifier = Modifier
                .padding(innerPaddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { onEvent(AddTransactionEvent.OnTitleChanged(it)) },
                label = { ThemedText("Title") }
            )

            OutlinedTextField(
                value = state.amount,
                onValueChange = { onEvent(AddTransactionEvent.OnAmountChanged(it)) },
                label = { ThemedText("Amount") }
            )

            Button(
                onClick = { onEvent(AddTransactionEvent.OnDateClicked(Date())) }
            ) {
                ThemedText("Select Date")
            }

            Button(
                onClick = { onEvent(AddTransactionEvent.OnCategoryClicked) }
            ) {
                ThemedText("Select Category")
            }

            Button(
                onClick = { onEvent(AddTransactionEvent.OnAddClicked) },
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                ThemedText("Add Transaction")
            }
        }
    }
}

@Preview
@Composable
fun PreviewAddTransactionScreen() {
    AddTransactionScreen(
        state = AddTransactionScreenState(),
        onEvent = {}
    )
}


