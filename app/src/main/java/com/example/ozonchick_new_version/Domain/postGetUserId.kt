package com.example.ozonchick_new_version.Domain

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.example.ozonchick_new_version.Data_Layer.network.GetUserId
import com.example.ozonchick_new_version.Data_Layer.network.RegistraitUser
import kotlinx.coroutines.CompletableDeferred

class postGetUserId(context : Context) {
    val context = context
    suspend fun postGetId(email:String): Boolean
    {
//        var accessGetId = CompletableDeferred<Boolean>()
        var responseGetID = GetUserId().GetId(email)
//        accessGetId.complete(responseGetID.acess)
//        accessGetId.await()
        if (responseGetID.acess){
            return true
        }
        else{
            return false
        }
    }
}