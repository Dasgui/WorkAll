package com.example.ozonchick_new_version.Presentation.ui.components

import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import io.ktor.websocket.Frame.Text

@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String = "Вы хотите продолжить?",
    dialogText: String,
    dialogConfirmText : String = "Подтверждаю",
    dialogDismissText : String = "Отказываюсь"
) {
    AlertDialog(
        title = {
            Text(
                text = dialogTitle,
                style = Tema.Text_20_20_Medium,
                color = MainColor
            )
        },
        text = {
            Text(
                text = dialogText,
                style = Tema.Text_20_20_Medium,
                color = MainColor
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    text = dialogConfirmText,
                    style = Tema.Text_16_16_Medium,
                    color = MainColor
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(
                    text = dialogDismissText,
                    style = Tema.Text_16_16_Medium,
                    color = MainColor
                )
            }
        },
        containerColor = BackgroundColor
    )
}