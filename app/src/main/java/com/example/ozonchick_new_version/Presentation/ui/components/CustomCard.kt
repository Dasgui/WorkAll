package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor

@Composable
fun CustomCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    shape: Shape = RectangleShape,
    colors: CardColors = CardDefaults.cardColors(containerColor = BackgroundColor),
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = BorderStroke(1.dp, MainColor),
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