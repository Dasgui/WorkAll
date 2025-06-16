package com.example.ozonchick_new_version.Presentation.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.ozonchick_new_version.Common.localAppTheme

//Основные цветаа
var MainColor by mutableStateOf(localAppTheme.mainColor)
var SecondColor by mutableStateOf(localAppTheme.secondColor)
var BackgroundColor by mutableStateOf(localAppTheme.backgroundColor)
var TransparentMainColor by mutableStateOf(localAppTheme.transparentColor)

val FirstMainColor = Color(0xFFFFFFFF)
val FirstSecondColor = Color(0xFF48CAE4)
val FirstBackgroundColor = Color(0xFF023E8A)
val FirstTransparentMainColor = Color(0xFFD2D2D2)

val SecondMainColor = Color(0xFFFFFFFF)
var SecondSecondColor = Color(0xFFcecece)
val SecondBackgroundColor = Color(0xFF000000)
val SecondTransparentMainColor = Color(0xFFD2D2D2)

val ThirdMainColor = Color(0xFFa9dd38)
val ThirdSecondColor = Color(0xFFe0e0e0)
val ThirdBackgroundColor = Color(0xFF000000)
val ThirdTransparentMainColor = Color(0xFF97C533)

val EnabledColor = Color(0xFF0077B6)
val SwitchOffColor = Color(0x80FFFFFF)

//основные цвета текста
val Gray_Color_Passive_Secondary = Color(0xFFA7A7A7)
val Gray_Color_Active_SideText = Color(0xFFCFCFCF)


val Text_Color_Active = Color(0xFF3A3A3A)

val Color_Red = Color(0xFFee204d)
val Switch_Color_Track = Color(0xFFD6D6D6)

//цвета кнопок
val Button_Color_Blue = Color(0xFF6AB8FD)
val Primary_Color = Color(0xFF0560FA)
val Text_Color_Orange = Color(0xFFEC8000)


val Background_Color_DarkMode = Color(0xFF000D1D)
val BackgroundTop_Color_DarkMode = Color(0xFF001B3B)




val Pink80 = Color(0xFFEFB8C8)
val Pink40 = Color(0xFF7D5260)
