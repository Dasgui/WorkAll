package com.example.ozonchick_new_version.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
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
    init {
        viewModelScope.launch (Dispatchers.IO) {
            val contactInformation = getInformation(contactId).await()
            if (contactInformation !is CustomResult.Failure) {
                contactsState.value = ScreenState.Success((contactInformation as CustomResult.Success).data)
                _informationList.value = contactInformation.data
            }
        }
    }
    val contactsState: MutableStateFlow<ScreenState<List<Vakans_contacts>>> =
        MutableStateFlow(ScreenState.Loading)
    private val _informationList: MutableStateFlow<List<Vakans_contacts>> =
        MutableStateFlow(emptyList())
    var informationList = _informationList.asStateFlow()
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