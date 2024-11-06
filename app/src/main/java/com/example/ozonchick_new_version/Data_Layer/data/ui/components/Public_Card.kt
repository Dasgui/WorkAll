package com.example.ozonchick_new_version.Data_Layer.data.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.backgroundColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.mainColor

@Composable
fun Public_Card(
    modifier: Modifier,
    shape: Shape = RectangleShape,
    colors: CardColors = CardDefaults.cardColors(containerColor = backgroundColor),
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = BorderStroke(1.dp, mainColor),
    content: @Composable() (ColumnScope.() -> Unit)
)
{
    Card(
        modifier = modifier,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        content = content
    )
}