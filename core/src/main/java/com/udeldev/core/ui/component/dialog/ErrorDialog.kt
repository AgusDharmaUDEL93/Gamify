package com.udeldev.core.ui.component.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorDialog(
    message: String,
    onConfirm: () -> Unit,
    title: String = "Error!"
) {
    AlertDialog(
        title = {
            Text(text = title)
        },
        text = {
            Text(
                text = message,
                textAlign = TextAlign.Center
            )

        },
        onDismissRequest = {},
        icon = {
            Icon(Icons.Default.Info, contentDescription = "Alert Dialog")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Okay")

            }
        }
    )
}