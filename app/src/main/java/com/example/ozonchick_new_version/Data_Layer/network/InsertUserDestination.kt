package com.example.ozonchick_new_version.Data_Layer.network

import com.example.ozonchick_new_version.Data_Layer.model.DTOMessage
import com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model.Destination_details
import com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model.infoZakaz
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertUserDestination {
    suspend fun InsertDestination(pacId:Int, ListZakaz: infoZakaz) : DTOMessage
    {
        var Info = CompletableDeferred<DTOMessage>()
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val insert_destination = supabase.from("destination_details").insert(
                    Destination_details(
                        pacId,
                        ListZakaz.Address,
                        ListZakaz.State_Country,
                        ListZakaz.PhoneNumber,
                        ListZakaz.Others,
                    )
                )
            }
        }
        catch (e:Exception)
        {
            Info.complete( DTOMessage(false,"Некоректно введенные данные"))
        }
        return Info.await()
    }
}