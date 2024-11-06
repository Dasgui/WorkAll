package com.example.ozonchick_new_version.Screens.Home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Card
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Scaffold
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.BackColorBlue
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.BackColorGreen
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Background
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_MainText
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.R

class Settings_Screen() : Screen {
    @Composable
    override fun Content() {
        Surface(
            color = Color_Background,
            modifier = Modifier.fillMaxSize()
        ) {
//            Settings_Screen_construct()
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Settings_Screen_construct() {
//    var navigator = LocalNavigator.current
//    Public_Scaffold(
//        bottomButton = false,
//        backButton = true
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "Цветовая схема",
//                style = Tema.Text_16_16_Medium,
//                color = Color_MainText.value,
//            )
//            Public_Button(
//                onClick = {
//                    Color_Background = Color.White
//                    Color_MainText = Color.Black
//                },
//                modifier = Modifier.width(300.dp)
//            )
//            {
//                Text(text = "Белая схема")
//            }
//            Public_Button(
//                onClick = {
//                    Color_Background = Color.Black
//                    Color_MainText = Color.White
//                },
//                modifier = Modifier.width(300.dp)
//            )
//            {
//                Text(text = "Чёрная схема")
//            }
//            Public_Button(
//                onClick = {
//                    Color_Background = Color.Yellow
//                    Color_MainText = Color.Black
//                },
//                modifier = Modifier.width(300.dp)
//            )
//            {
//                Text(text = "Жёлтая схема")
//            }
//            Public_Button(
//                onClick = {
//                    Color_Background.value = BackColorBlue
//                    Color_MainText = Color.Black
//                },
//                modifier = Modifier.width(300.dp)
//            )
//            {
//                Text(text = "Синяя схема")
//            }
//            Public_Button(
//                onClick = {
//                    Color_Background.value = BackColorGreen
//                    Color_MainText = Color.Black
//                },
//                modifier = Modifier.width(300.dp)
//            )
//            {
//                Text(text = "Зелённая схема")
//            }
//        }
//    }
//}