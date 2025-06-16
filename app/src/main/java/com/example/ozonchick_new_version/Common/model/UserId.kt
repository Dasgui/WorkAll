package com.example.ozonchick_new_version.Common.model

import androidx.compose.ui.graphics.Color
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondColor

data class UserId(
    var id : Int,
    var type : UserType
)

data class UserMainInformation(
    var token : String,
    var type : UserType,
    var name : String,
    var id : Int
)

data class AppTheme (
    var mainColor : Color,
    var secondColor : Color,
    var backgroundColor : Color,
    var transparentColor : Color,
)