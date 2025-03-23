package com.example.ozonchick_new_version.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.isLocalVersion
import com.example.ozonchick_new_version.Common.localListContacts
import com.example.ozonchick_new_version.Presentation.Models.ProfileUIModel
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Data.network.supabase
import com.example.ozonchick_new_version.Domain.GetContactInformationUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val getContactInformationUseCase: GetContactInformationUseCase,
    private val contactId : Int
) : ViewModel() {

    val contactsState: MutableStateFlow<ScreenState<List<Vakans_contacts>>> =
        MutableStateFlow(ScreenState.Loading)
    private val _informationList: MutableStateFlow<Vakans_contacts> =
        MutableStateFlow(Vakans_contacts(0, "", "", "", ""))
    var informationList = _informationList.asStateFlow()

    init {
        if (isLocalVersion == true) {
            val item = localListContacts.find { it.vakans_id == contactId }
            contactsState.value = if (item != null) ScreenState.Success(localListContacts) else ScreenState.Failure(0, "asd")
            _informationList.value = item ?: Vakans_contacts(0, "", "", "", "")
        } else {
            viewModelScope.launch (Dispatchers.IO) {
                val contactInformation = getInformation(contactId).await()
                if (contactInformation !is CustomResult.Failure) {
                    contactsState.value = ScreenState.Success((contactInformation as CustomResult.Success).data)
                    _informationList.value = contactInformation.data[0]
                }
            }
        }
    }

    suspend fun getInformation(id: Int) : Deferred<CustomResult<List<Vakans_contacts>>> {
        return coroutineScope { async(Dispatchers.IO) { getContactInformationUseCase(id) } }
//        val result = supabase.from("vakans_contacts")
//            .select()
//            {
//                filter {
//                    Vakans_contacts::vakans_id eq id
//                }
//            }.decodeList<Vakans_contacts>()
//        _informationList.value = result
//        contactsState.value = ScreenState.Success(result)
    }
}