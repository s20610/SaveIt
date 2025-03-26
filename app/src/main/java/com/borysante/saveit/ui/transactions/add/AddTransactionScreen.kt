@file:OptIn(ExperimentalMaterial3Api::class)

package com.borysante.saveit.ui.transactions.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.borysante.saveit.R
import com.borysante.saveit.data.dto.transactions.TransactionCategory
import com.borysante.saveit.ui.components.ThemedText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AddTransactionScreen(
    state: AddTransactionScreenState,
    snackbarHostState: SnackbarHostState,
    onEvent: (AddTransactionEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
    TopAppBar(
        title = { Text(text = stringResource(R.string.add_transaction)) }, //Use string resource
        navigationIcon = {
            IconButton(onClick = { onEvent(AddTransactionEvent.OnCancelClicked) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        },
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}

@Composable
fun AddTransactionContent(
    state: AddTransactionScreenState,
    onEvent: (AddTransactionEvent) -> Unit,
    innerPaddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Use Arrangement.spacedBy for consistent spacing
    ) {
        OutlinedTextField(
            value = state.title,
            onValueChange = { onEvent(AddTransactionEvent.OnTitleChanged(it)) },
            label = { ThemedText(text = stringResource(R.string.title)) }, //Use string resource
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.amount,
            onValueChange = { onEvent(AddTransactionEvent.OnAmountChanged(it)) },
            label = { ThemedText(text = stringResource(R.string.amount)) }, //Use string resource
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Restrict to numeric input
        )

        DateSelectionField(
            selectedDate = state.date,
            onDateSelected = { onEvent(AddTransactionEvent.OnDateChanged(it)) },
            modifier = Modifier.fillMaxWidth()
        )

        CategorySelectionField(
            selectedCategory = state.category,
            onCategorySelected = { onEvent(AddTransactionEvent.OnCategoryChanged(it)) },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { onEvent(AddTransactionEvent.OnAddClicked) },
            modifier = Modifier.fillMaxWidth()
        ) {
            ThemedText(text = stringResource(R.string.add_transaction)) //Use string resource
        }
    }
}

@Composable
fun DateSelectionField(
    selectedDate: Date?,
    onDateSelected: (Date) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val formattedDate =
        selectedDate?.let { dateFormatter.format(it) } ?: stringResource(R.string.select_date)

    OutlinedTextField(
        value = formattedDate,
        onValueChange = {},
        readOnly = true,
        label = { ThemedText(text = stringResource(R.string.date)) },
        trailingIcon = {
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = stringResource(R.string.select_date)
                )
            }
        },
        modifier = modifier.clickable { showDialog = true }
    )

    if (showDialog) {
        DatePickerModal(
            onDateSelected = { selectedMillis ->
                if (selectedMillis != null) {
                    onDateSelected(Date(selectedMillis))
                }
            },
            onDismiss = { showDialog = false }
        )
    }
}


@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text(stringResource(R.string.ok)) //Use string resource
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel)) //Use string resource
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun CategorySelectionField(
    selectedCategory: TransactionCategory?,
    onCategorySelected: (TransactionCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedCategory?.categoryName
                ?: stringResource(R.string.select_category), //Use string resource
            onValueChange = {},
            label = { ThemedText(text = stringResource(R.string.category)) },  //Use string resource
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            TransactionCategory.entries.forEach { category ->
                DropdownMenuItem(
                    text = { Text(text = category.categoryName) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddTransactionScreen() {
    AddTransactionScreen(
        state = AddTransactionScreenState(),
        onEvent = {},
        snackbarHostState = SnackbarHostState()
    )
}