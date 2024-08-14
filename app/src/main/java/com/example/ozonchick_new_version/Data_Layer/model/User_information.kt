package com.example.ozonchick_new_version.Data_Layer.model

import android.provider.ContactsContract.CommonDataKinds.Email
import kotlinx.serialization.Serializable

@Serializable
data class User_information(
    val user_id:Int,
    val user_name:String,
    val user_phone_number:String,
    var user_email:String
)
