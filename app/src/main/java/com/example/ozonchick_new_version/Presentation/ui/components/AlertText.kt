package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data.strings.AppErrors
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor

@Composable
fun AlertText(text : String = AppErrors.INTERNET.description, isBackButton : Boolean = false, backOn : () -> Unit = {}) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = Tema.Text_24_24_Bold,
            color = MainColor,
            textAlign = TextAlign.Center
        )
        VerticalSpacer(30.dp)
        if (isBackButton) {
            CustomButton(
                onClick = {backOn()},
                placeText = "Отменить поиск"
            )
        }
    }
}