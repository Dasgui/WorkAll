package com.example.ozonchick_new_version.Presentation.ui.components.SignUp_LogIn_Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Presentation.ui.theme.BorderSize
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor

@Composable
fun SignUp_LogIn_Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(46.dp),
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    colors: ButtonColors,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = BorderStroke(BorderSize, MainColor),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable() (RowScope.() -> Unit)
) {
    Button(
        onClick = onClick,
        colors = colors,
        modifier = modifier,
        shape = shape,
        content = content,
        interactionSource = interactionSource,
        contentPadding = contentPadding,
        border = border,
        elevation = elevation,
        enabled = enabled
    )
}