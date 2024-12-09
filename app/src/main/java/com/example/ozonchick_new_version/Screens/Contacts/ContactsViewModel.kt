package com.example.ozonchick_new_version.Screens.Contacts

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_contacts
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import com.example.ozonchick_new_version.Data_Layer.network.supabase
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactsViewModel : ViewModel() {
    private val _informationList: MutableStateFlow<List<Vakans_contacts>> = MutableStateFlow(emptyList())
    var informationList = _informationList.asStateFlow()
    suspend fun getInformation(id:Int) {
        val result = supabase.from("vakans_contacts")
            .select()
            {
                filter {
                    Vakans_contacts::vakans_id eq id
                }
            }.decodeList<Vakans_contacts>()
        _informationList.value = result
    }
}