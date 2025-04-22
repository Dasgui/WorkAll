package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.TextFieldStates
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.R

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    states: TextFieldStates = TextFieldStates.Normal,
    placeText: String = "",
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(55.dp),
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholder: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    readOnly : Boolean = false
) {
    val trailingIconVisible: MutableState<Boolean> = remember { mutableStateOf(true) }
    OutlinedTextField(
        value = value,
        readOnly = readOnly,
        onValueChange = onValueChange,
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        enabled = enabled,
        textStyle = Tema.Text_20_20_Medium,
        placeholder = {
            if (states == TextFieldStates.Password || states == TextFieldStates.Email) {
                Text(
                    text = if (states == TextFieldStates.Password) "***********" else "*******@mail.com",
                    style = Tema.Text_20_20_Medium,
                    color = MainColor
                )
            } else {
                Text(
                    text = placeText,
                    style = Tema.Text_20_20_Medium,
                    color = MainColor
                )
            }
        },
        leadingIcon = if (states is TextFieldStates.Normal) {
            null
        } else {
            leadingIcon
        },
        trailingIcon = when (states) {
            is TextFieldStates.Password -> {
                {
                    Image(
                        painter = painterResource(id = if (trailingIconVisible.value) R.drawable.eye_close else R.drawable.eye_open),
                        contentDescription = "Показать пароль",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { trailingIconVisible.value = !trailingIconVisible.value },
                        colorFilter = ColorFilter.tint(MainColor)
                    )
                }
            }
            else -> trailingIcon
        },
        maxLines = maxLines,
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = BackgroundColor,
            unfocusedContainerColor = BackgroundColor,
            focusedIndicatorColor = MainColor,
            unfocusedIndicatorColor = MainColor,
            focusedTextColor = MainColor,
            unfocusedTextColor = MainColor
        ),
        visualTransformation = if (states is TextFieldStates.Password && trailingIconVisible.value) PasswordVisualTransformation() else VisualTransformation.None
    )
}
