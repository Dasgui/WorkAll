package com.example.ozonchick_new_version.Data_Layer.data.ui.components.SignUp_LogIn_Components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Primary_Color
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Switch_Color_Track

@Composable
fun SignUp_LogIn_Switch(

    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    thumbContent: @Composable() (() -> Unit)? = null,
    enabled: Boolean = true,
    colors: SwitchColors = SwitchDefaults.colors(
        checkedTrackColor = Primary_Color,
        uncheckedTrackColor = Switch_Color_Track,
        checkedBorderColor = Color.Transparent,
        uncheckedBorderColor = Color.Transparent,
        checkedIconColor = Color.White,
        uncheckedIconColor = Color.White,
        checkedThumbColor = Color.White,
        uncheckedThumbColor = Color.White,
    ),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        thumbContent = thumbContent,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource
    )
}