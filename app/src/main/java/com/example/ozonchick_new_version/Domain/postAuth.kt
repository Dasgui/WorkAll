package com.example.ozonchick_new_version.Domain

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.ozonchick_new_version.Data_Layer.network.AuthUser
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import okhttp3.internal.wait

class postAuth(/*context:Context*/) {
/*val context = context*/
    suspend fun postAuthEmail(mail:String,password:String, rememberUser : Boolean):Boolean{
        var access = CompletableDeferred<Boolean>()
        var response = AuthUser().AuthEmail(mail,password, rememberUser)
        access.complete(response.acess)
        access.await()
    return if (response.acess){
        /*            val toast =Toast.makeText(context,response.message,Toast.LENGTH_LONG)
            toast.setGravity(Gravity.FILL_VERTICAL,0,0)
            toast.show()*/
        true
    } else{
        /*            val toast =Toast.makeText(context,response.message,Toast.LENGTH_LONG)
            toast.setGravity(Gravity.FILL_VERTICAL,0,0)
            toast.show()*/
        false
    }
    }

}