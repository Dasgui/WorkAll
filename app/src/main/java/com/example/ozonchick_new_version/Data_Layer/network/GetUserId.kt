package com.example.ozonchick_new_version.Data_Layer.network

import com.example.ozonchick_new_version.Data_Layer.data.db.idUser
import com.example.ozonchick_new_version.Data_Layer.model.DTOMessage
import com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model.User_information
import com.google.gson.Gson
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetUserId {
    suspend fun GetId(email:String) : DTOMessage
    {
        var Info = CompletableDeferred<DTOMessage>()
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val take_id = supabase.from("user_information").select(Columns.list("user_id", "email"))
                {
                    filter {
                        User_information::email eq email
                    }
                }
                var note = Gson().fromJson(take_id.data, Array<User_information>::class.java)

                idUser.value = note[0].user_id
                Info.complete(DTOMessage(true, "Успешное получение"))
            }
        }
        catch (e:Exception)
        {
            Info.complete( DTOMessage(false,"Не удалось получить данные"))
        }
        return Info.await()
    }
}