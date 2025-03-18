package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor

@Composable
fun FailAlertDialog(
    title: String,
    text: String,
    onOkClick : () -> Unit,
) {
    AlertDialog(
        containerColor = BackgroundColor,
        title = {
            Text(text = title, style = Tema.Text_24_24_Medium, color = MainColor)
        },
        text = {
            Text(text = text, style = Tema.Text_20_20_Medium, color = MainColor)
        },
        onDismissRequest = {
            onOkClick()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onOkClick()
                }
            ) {
                Text("Хорошо", style = Tema.Text_20_20_Medium, color = MainColor)
            }
        },

    )
}