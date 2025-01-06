package com.borysante.saveit.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.VisualTransformation
import com.borysante.saveit.data.dto.error.InputError

@Composable
fun ThemedOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit,
    label: @Composable () -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    error: InputError? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
) {
    OutlinedTextField(
        modifier = modifier.onFocusChanged { focusState ->
            onFocusChange(focusState.isFocused)
        },
        value = value,
        onValueChange = { onValueChange(it) },
        label = label,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        supportingText = {
            if (error != null) {
                ThemedText(error.errorMessage, color = MaterialTheme.colorScheme.error)
            }
        },
        isError = error != null,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        maxLines = maxLines,
    )
}