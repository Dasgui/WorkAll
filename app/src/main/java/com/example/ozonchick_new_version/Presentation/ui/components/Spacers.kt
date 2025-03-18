package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpacer(height : Dp) {
    Spacer(Modifier.height(height))
}

@Composable
fun HorizontalSpacer(width : Dp) {
    Spacer(Modifier.width(width))
}