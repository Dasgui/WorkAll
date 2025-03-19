package com.example.ozonchick_new_version.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.example.ozonchick_new_version.Data.model.ProfileUIModel
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Data.network.supabase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ContactsViewModel : ViewModel() {
    val contactsState: MutableStateFlow<ScreenState<List<Vakans_contacts>>> =
        MutableStateFlow(ScreenState.Loading)
    private val _informationList: MutableStateFlow<List<Vakans_contacts>> =
        MutableStateFlow(emptyList())
    var informationList = _informationList.asStateFlow()
    suspend fun getInformation(id: Int) {
        val result = supabase.from("vakans_contacts")
            .select()
            {
                filter {
                    Vakans_contacts::vakans_id eq id
                }
            }.decodeList<Vakans_contacts>()
        _informationList.value = result
        contactsState.value = ScreenState.Success(result)
    }
}