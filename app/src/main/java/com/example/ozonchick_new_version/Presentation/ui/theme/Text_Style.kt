package com.example.ozonchick_new_version.Presentation.ui.theme

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.TextStyle
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema

var mainTextStyle: MutableState<TextStyle>  = mutableStateOf(Tema.Text_24_24_Bold)
var middleTextStyle: MutableState<TextStyle> = mutableStateOf(Tema.Text_20_20_Medium)
var secondTextStyle: MutableState<TextStyle> = mutableStateOf(Tema.Text_16_16_Medium)