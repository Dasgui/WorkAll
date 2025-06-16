package com.example.ozonchick_new_version.Common

import androidx.compose.ui.graphics.Color
import com.example.ozonchick_new_version.Common.model.AppTheme
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.model.UserMainInformation

var publicUserId: UserId = UserId(0, UserType.None)

var localUserInformation: UserMainInformation = UserMainInformation("", UserType.None, "", 0)
var localAppTheme: AppTheme = AppTheme(
    Color(0xFFFFFFFF),
    Color(0xFF48CAE4),
    Color(0xFF023E8A),
    Color(0xFFD2D2D2)
)

fun appendLocalUserInformation(token: String, id: Int, name: String, userType: UserType) {
    localUserInformation.token = "Bearer $token"
    localUserInformation.id = id
    localUserInformation.name = name
    localUserInformation.type = userType
}