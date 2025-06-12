package com.example.ozonchick_new_version.Common

import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.model.UserMainInformation

var publicUserId : UserId = UserId(0, UserType.None)

var localUserInformation : UserMainInformation = UserMainInformation("", UserType.None, "", 0)

fun appendLocalUserInformation(token : String, id : Int, name : String, userType : UserType) {
    localUserInformation.token = "Bearer $token"
    localUserInformation.id = id
    localUserInformation.name = name
    localUserInformation.type = userType
}