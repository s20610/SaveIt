package com.borysante.saveit.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.borysante.saveit.ui.theme.SaveItTheme

@Composable
fun ThemedOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
    textColor: Color? = null,
    backgroundColor: Color? = null,
    disabled: Boolean = false
) {
    val buttonTextColor = textColor ?: MaterialTheme.colorScheme.onSurfaceVariant

    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        contentPadding = contentPadding,
        enabled = !disabled,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor ?: Color.Transparent,
            contentColor = buttonTextColor,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = buttonTextColor
        ),
        border = ButtonDefaults.outlinedButtonBorder(!disabled)
    ) {
        ThemedText(text = text, color = buttonTextColor)
    }
}

@Preview(showBackground = true)
@Composable
fun ThemedOutlinedButtonPreview() {
    SaveItTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ThemedOutlinedButton(
                text = "Click Me",
                onClick = { /* Handle click */ },
                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            ThemedOutlinedButton(
                text = "Disabled Button",
                onClick = { /* Handle click */ },
                disabled = true,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
