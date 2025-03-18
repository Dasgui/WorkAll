package com.example.ozonchick_new_version.Domain

import com.example.ozonchick_new_version.Data.network.RegistraitUser
import kotlinx.coroutines.CompletableDeferred

class postRegistrait() {
    suspend fun postRegistraitEmail(name:String, phoneNumber:String, email:String, password:String, /*userStatus:String, INN:String*/) : Boolean{

        var accessRegistrait = CompletableDeferred<Boolean>()
        var responseRegistrait = RegistraitUser().RegisstraitEmail(name, phoneNumber, email, password/*, userStatus, INN*/)
        accessRegistrait.complete(responseRegistrait.acess)
        accessRegistrait.await()

        if (responseRegistrait.acess){
//            val toast = Toast.makeText(context,responseRegistrait.message, Toast.LENGTH_LONG)
//            toast.setGravity(Gravity.FILL_VERTICAL,0,0)
//            toast.show()
            return true
        }
        else{
//            val toast = Toast.makeText(context,responseRegistrait.message, Toast.LENGTH_LONG)
//            toast.setGravity(Gravity.FILL_VERTICAL,0,0)
//            toast.show()
            return false
        }
    }
}
