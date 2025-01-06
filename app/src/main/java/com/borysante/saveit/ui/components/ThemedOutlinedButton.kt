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
    enabled: Boolean = true,
    isProminent: Boolean = false
) {
    val buttonTextColor = when {
        isProminent -> MaterialTheme.colorScheme.onPrimary
        textColor != null -> textColor
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    val disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    val disabledBackgroundColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.12f)
    val prominentBackgroundColor = MaterialTheme.colorScheme.primary

    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        contentPadding = contentPadding,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isProminent) {
                if (enabled) prominentBackgroundColor else disabledBackgroundColor
            } else backgroundColor ?: Color.Transparent,
            contentColor = if (enabled) buttonTextColor else disabledTextColor,
            disabledContainerColor = if (isProminent) disabledBackgroundColor else Color.Transparent,
            disabledContentColor = disabledTextColor
        ),
        border = if (isProminent) null else ButtonDefaults.outlinedButtonBorder(enabled)
    ) {
        ThemedText(
            text = text,
            color = if (enabled) buttonTextColor else disabledTextColor
        )
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
                isProminent = true
            )
            ThemedOutlinedButton(
                text = "Disabled Button",
                onClick = { /* Handle click */ },
                enabled = false,
                textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
