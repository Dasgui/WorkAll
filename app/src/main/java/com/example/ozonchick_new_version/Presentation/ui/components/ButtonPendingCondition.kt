package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BorderSize
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondColor

@Composable
fun ButtonPendingCondition(
    enabled: Boolean,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = { if (enabled) onClick() },
        modifier = Modifier
            .clickable(enabled = enabled) { if (enabled) onClick() }
            .fillMaxWidth()
            .height(46.dp),
        enabled = true,
        shape = RectangleShape,
        colors = if (enabled) {
            ButtonDefaults.buttonColors(containerColor = BackgroundColor, contentColor = MainColor)
        } else {
            ButtonDefaults.buttonColors(containerColor = BackgroundColor, contentColor = SecondColor)
        },
        elevation = ButtonDefaults.buttonElevation(),
        border = BorderStroke(BorderSize, MainColor),
        contentPadding = ButtonDefaults.ContentPadding,
    ) {
        Text(
            text = text,
            style = Tema.Text_24_24_Bold
        )
    }
}