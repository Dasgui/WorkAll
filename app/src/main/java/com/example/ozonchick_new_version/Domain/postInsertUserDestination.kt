package com.example.ozonchick_new_version.Domain

import android.content.Context
import com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model.infoZakaz
import com.example.ozonchick_new_version.Data_Layer.network.GetUserId
import com.example.ozonchick_new_version.Data_Layer.network.InsertUserDestination
import kotlinx.coroutines.CompletableDeferred

class postInsertUserDestination() {
    suspend fun postInsertDestination(pacId: Int, ListZakaz: infoZakaz): Boolean {
        var accessInsertDistination = CompletableDeferred<Boolean>()
        var responseInsertDestination = InsertUserDestination().InsertDestination(pacId,ListZakaz)
        accessInsertDistination.complete(responseInsertDestination.acess)
        accessInsertDistination.await()
        if (responseInsertDestination.acess) {
            return true
        } else {
            return false
        }
    }
}