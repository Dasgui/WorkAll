package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondColor

@Composable
fun RowTextImage(icon: Int, title: String, text: String = "", isDescription: Boolean = false, textColor : Color = MainColor) {

    if (isDescription) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = SecondColor
                )
                Text(
                    text = title,
                    style = Tema.Text_24_24_Medium,
                    color = textColor,
                    textAlign = TextAlign.Start
                )
            }
            Text(
                text = text,
                style = Tema.Text_24_24_Medium,
                color = textColor,
                textAlign = TextAlign.Start
            )
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = SecondColor
            )
            //Расположение Location
            Text(
                text = title,
                style = Tema.Text_24_24_Medium,
                color = textColor
            )
        }
    }
}