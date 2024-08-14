package com.example.ozonchick_new_version.Data_Layer.data.ui.components.Onboarding_Components

import android.content.Context
import android.content.res.Configuration
import android.util.Log
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Primary_Color
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Primary_Color_DarkMode
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Gray_Color_Passive_Secondary


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