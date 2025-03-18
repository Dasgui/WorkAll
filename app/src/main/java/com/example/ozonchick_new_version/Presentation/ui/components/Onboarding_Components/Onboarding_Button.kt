package com.example.ozonchick_new_version.Presentation.ui.components.Onboarding_Components

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import com.example.ozonchick_new_version.Presentation.ui.theme.Gray_Color_Passive_Secondary
import com.example.ozonchick_new_version.Presentation.ui.theme.Primary_Color


@Composable
fun Onboarding_Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
        .width(100.dp)
        .height(50.dp),
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(0.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = BorderStroke(1.dp, color = Primary_Color),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable() (RowScope.() -> Unit),

    ) {
    val context2 = LocalContext.current
    Button(
        onClick = onClick,
        colors = if (isDarkThemeEnabled(context2)) {
            ButtonDefaults.buttonColors(containerColor = Gray_Color_Passive_Secondary)
        } else {
            colors
        },
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

fun isDarkThemeEnabled(context: Context): Boolean {


    val currentNightMode =
        context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return currentNightMode == Configuration.UI_MODE_NIGHT_YES

}