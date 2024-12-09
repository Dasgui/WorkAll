package com.example.ozonchick_new_version.Data_Layer.data.db

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.registry.ScreenProvider
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator

var emailSharedUser: MutableState<String> = mutableStateOf("")
val idSharedUser: MutableState<Int> = mutableIntStateOf(0)

var accessSharedRegistrait: MutableState<Boolean> = mutableStateOf(false)
var userSharedId :MutableState<Int> = mutableStateOf(0)
var isDark:MutableState<Boolean> = mutableStateOf(true)
/*var emailUser: MutableState<String> = mutableStateOf("")*/

