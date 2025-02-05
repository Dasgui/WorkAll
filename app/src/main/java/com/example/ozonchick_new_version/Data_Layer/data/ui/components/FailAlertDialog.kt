package com.example.ozonchick_new_version.Data_Layer.data.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.backgroundColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.mainColor
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.UserInformationAuth
import com.example.ozonchick_new_version.Presentation.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun FailAlertDialog(
    title: String,
    text: String,
    onOkClick : () -> Unit,
) {
    AlertDialog(
        title = {
            Text(text = title, style = Tema.Text_20_20_Medium)
        },
        text = {
            Text(text = text, style = Tema.Text_16_16_Medium)
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
                Text("Хорошо", style = Tema.Text_16_16_Medium)
            }
        },
        containerColor = backgroundColor,
        textContentColor = mainColor,
        titleContentColor = mainColor
    )
}