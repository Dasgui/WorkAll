package com.example.ozonchick_new_version.Domain

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.example.ozonchick_new_version.Data_Layer.model.TestModel_Get
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import com.example.ozonchick_new_version.Data_Layer.network.AuthUser
import com.example.ozonchick_new_version.Data_Layer.network.GetContacts
import kotlinx.coroutines.CompletableDeferred

class postGetContacts(context: Context) {
    val context = context
    suspend fun postGetContact(mail: String, number: Int): TestModel_Get {
        var accessAuth = CompletableDeferred<Boolean>()
        var responseGet = GetContacts().GetContact(mail, number)
        accessAuth.complete(responseGet.list.isNotEmpty())
        accessAuth.await()
        if (responseGet.list.isNotEmpty()){
//            val toast = Toast.makeText(context,responseGet.list.toString(), Toast.LENGTH_LONG)
//            toast.setGravity(Gravity.FILL_VERTICAL,0,0)
//            toast.show()
            return TestModel_Get(responseGet.list, responseGet.number)
        }
        else{
//            val toast = Toast.makeText(context,responseGet.list.toString(), Toast.LENGTH_LONG)
//            toast.setGravity(Gravity.FILL_VERTICAL,0,0)
//            toast.show()
            return TestModel_Get(responseGet.list, responseGet.number)
        }
    }
}