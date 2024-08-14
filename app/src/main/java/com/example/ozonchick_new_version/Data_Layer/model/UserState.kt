package com.example.ozonchick_new_version.Data_Layer.model

sealed class UserState(){
    object Loading:UserState()
    data class Success(val message:String):UserState()
    data class Error(val message:String):UserState()
}
