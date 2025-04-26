package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor

@Composable
fun CheckBoxField (
    checked : Boolean,
    onChange : (Boolean) -> Unit,
    text : String
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomCheckBox(
            checked = checked,
            onChange = {
                onChange(it)
            },
            modifier = Modifier
                .size(24.dp)
                .padding(start = 0.dp)
        )
        HorizontalSpacer(10.dp)
        Text(
            text = text,
            style = Tema.Text_20_20_Medium,
            color = MainColor
        )
    }
}