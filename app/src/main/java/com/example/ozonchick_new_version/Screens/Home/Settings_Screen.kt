package com.example.ozonchick_new_version.Screens.Home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Background

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