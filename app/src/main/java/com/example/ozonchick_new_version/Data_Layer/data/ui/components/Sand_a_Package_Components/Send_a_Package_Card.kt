package com.example.ozonchick_new_version.Data_Layer.data.ui.components.Sand_a_Package_Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun Send_a_Package_Card(
    modifier: Modifier = Modifier.padding(top = 5.dp).fillMaxWidth(),
    shape: Shape = RectangleShape,
    colors: CardColors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
    elevation: CardElevation = CardDefaults.cardElevation(10.dp),
    border: BorderStroke? = null,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    Card (
        modifier = modifier,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        content = content
    )
}