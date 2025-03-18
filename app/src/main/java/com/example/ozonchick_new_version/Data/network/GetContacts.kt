package com.example.ozonchick_new_version.Data.network

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.ozonchick_new_version.Data.model.TestModel_Get
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetContacts {
    suspend fun GetContact(mail: String, number: Int): TestModel_Get
    {
        var list:MutableList<Vakans_contacts> = mutableListOf()
        var num:MutableState<Int> = mutableStateOf(0)
        CoroutineScope(Dispatchers.IO).launch {
            val result = supabase.from("vakans_contacts")
                .select(Columns.list("vakans_number", "vakans_mail", "vakans_raspolog"))
                {
                    filter {
                        Vakans_contacts::vakans_mail eq mail
                    }
                }.decodeList<Vakans_contacts>()
            list = result.toMutableList()
            num.value = number
            /*name = spisokVakans[0].vakans_name*/
        }
        return TestModel_Get(list,num)
    }
}