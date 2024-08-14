package com.example.ozonchick_new_version.Data_Layer.data.ui.components.Test_Components.mini_components

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_MainText
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.Home.Spisok_Predlogenii_Screen

@Composable
fun Clickable_Icon(
    navigator: Navigator?,
    screen: Screen,

    iconId: Int,
    sizeIcon : Dp,
    colorIcon : Color,

    bottomText : String,
    bottomTextStyle : TextStyle,
    colorBottomText : Color,

) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { navigator?.push(screen) }
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "Иконка",
            modifier = Modifier
                .size(sizeIcon),
            tint = colorIcon
        )
        Text(
            text = bottomText,
            style = bottomTextStyle,
            color = colorBottomText,
            modifier = Modifier.padding(top = 3.dp)
        )
    }
}