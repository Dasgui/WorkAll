package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.R
import kotlinx.coroutines.delay

@Composable
fun CustomLoading(isLoading:Boolean = true) {
    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val rorate = remember {
            mutableFloatStateOf(0f)
        }
        Image(
            painter = painterResource(id = R.drawable.cruck),
            contentDescription = null,
            modifier = Modifier
                .rotate(rorate.floatValue)
                .size(120.dp),
            colorFilter = ColorFilter.tint(MainColor)
        )
        LaunchedEffect(Unit) {
            while (true) {
                rorate.floatValue += 10f
                delay(35)
            }
        }
    }
}