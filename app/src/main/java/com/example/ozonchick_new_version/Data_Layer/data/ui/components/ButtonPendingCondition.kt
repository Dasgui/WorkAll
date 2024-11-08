package com.example.ozonchick_new_version.Data_Layer.data.ui.components

import android.graphics.drawable.shapes.Shape
import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.BorderSize
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.backgroundColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.mainColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.secondaryElementColor

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
            ButtonDefaults.buttonColors(containerColor = backgroundColor, contentColor = mainColor)
        } else {
            ButtonDefaults.buttonColors(containerColor = backgroundColor, contentColor = secondaryElementColor)
        },
        elevation = ButtonDefaults.buttonElevation(),
        border = BorderStroke(BorderSize, mainColor),
        contentPadding = ButtonDefaults.ContentPadding,
    ) {
        Text(
            text = text,
            style = Tema.Text_24_24_Bold
        )
    }
}