package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor

@Composable
fun NameTextField(
    title: String,
    holderText: String,
    field: MutableState<String>,
    maxChar: Int = 0,
    onlyNumbers : Boolean = false,
    height : Dp = 55.dp,
    singleLine : Boolean = true,
    spaceBy : Dp = 10.dp,
    enabled : Boolean = true,
    indicatorColor : Color = MainColor
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spaceBy)
    ) {
        Text(
            text = title,
            style = Tema.Text_24_24_Bold,
            maxLines = 1,
            color = MainColor,
        )
        CustomOutlinedTextField(
            value = field.value,
            modifier = Modifier.fillMaxWidth().height(height),
            onValueChange = {
                if (maxChar == 0) {
                    field.value = it
                } else {
                    if (onlyNumbers) {
                        if (it.length <= maxChar && it.isDigitsOnly()) {
                            field.value = it
                        }
                    } else {
                        if (it.length <= maxChar) {
                            field.value = it
                        }
                    }
                }
            },
            enabled = enabled,
            singleLine = singleLine,
            placeText = holderText,
            keyboardOptions = if (onlyNumbers) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions.Default,
            indicatorColor = indicatorColor
        )
    }
}

@Composable
fun NameTextField(
    title: String,
    information: String,
    height : Dp = 55.dp,
    singleLine : Boolean = true,
    spaceBy : Dp = 10.dp,
    enabled : Boolean = true
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spaceBy)
    ) {
        Text(
            text = title,
            style = Tema.Text_24_24_Bold,
            maxLines = 1,
            color = MainColor,
        )
        CustomOutlinedTextField(
            value = information,
            modifier = Modifier.fillMaxWidth().height(height),
            onValueChange = {},
            enabled = enabled,
            singleLine = singleLine,
        )
    }
}