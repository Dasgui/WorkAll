package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor

@Composable
fun CustomButton(
    onClick : () -> Unit,
    placeText : String = "",
    enabled: Boolean = true,
    modifier: Modifier = Modifier.fillMaxWidth().height(45.dp),
    shape: Shape = RectangleShape,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = BackgroundColor,
        contentColor = MainColor,
        disabledContainerColor = BackgroundColor,
        disabledContentColor = MainColor
    ),
    border : BorderStroke? = BorderStroke(1.dp, MainColor)
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = shape,
        border = border,
        colors = colors
    ) {
        Text(
            text = placeText,
            style = Tema.Text_24_24_Bold,
        )
    }
}