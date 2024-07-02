package com.borysante.saveit.ui.transactions.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
        topBar = {
            AddTransactionTopBar()
        },
        content = { innerPadding ->
            AddTransactionContent(state, onEvent, innerPadding)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionTopBar() {
    TopAppBar(title = {
        Text(text = "Add transaction")
    }, modifier = Modifier.padding(horizontal = 8.dp))
}

@Composable
fun AddTransactionContent(
    state: AddTransactionScreenState,
    onEvent: (AddTransactionEvent) -> Unit,
    innerPaddingValues: PaddingValues
){
    with(state) {
        Column(modifier = Modifier.padding(innerPaddingValues).padding(horizontal = 24.dp)) {
            TextField(
                value = title,
                onValueChange = { onEvent(AddTransactionEvent.OnTitleChanged(it)) },
                label = { ThemedText("Title") }
            )

            TextField(
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


