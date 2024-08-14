package com.example.ozonchick_new_version.Data_Layer.data.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_MainText
import com.example.ozonchick_new_version.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Public_Loading(isLoading:Boolean) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 90.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var rorate by remember {
            mutableStateOf(0f)
        }
        var DownImage by remember {
            mutableStateOf(R.drawable.cruck)
        }
        Image(
            painter = painterResource(id = DownImage),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 100.dp)
                .size(120.dp)
                .rotate(rorate),
            colorFilter = ColorFilter.tint(Color_MainText.value)
        )
        LaunchedEffect(key1 = true) {

            CoroutineScope(Dispatchers.IO).launch {
                while (!isLoading) {
                    rorate = rorate + 1f
                    delay(1)
                }
            }
        }
    }
}