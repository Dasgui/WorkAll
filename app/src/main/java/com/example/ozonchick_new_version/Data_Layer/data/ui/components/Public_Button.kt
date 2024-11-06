package com.example.ozonchick_new_version.Data_Layer.data.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.BorderSize
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.backgroundColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.mainColor

@Composable
fun Public_Button(
    fillButton: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    colors: ButtonColors =
        if (fillButton) {
            ButtonDefaults.buttonColors(
                containerColor = mainColor,
                contentColor = backgroundColor
            )
        } else {
            ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = mainColor
            )
        },
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = BorderStroke(BorderSize, mainColor),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable() (RowScope.() -> Unit)
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        content = content
    )
}
