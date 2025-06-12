package com.example.ozonchick_new_version.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyInformation
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyResponse
import com.example.ozonchick_new_version.Data.model.VacancyAdditionalInformation
import com.example.ozonchick_new_version.Domain.GetContactInformationUseCase
import com.example.ozonchick_new_version.Domain.GetVacancyInformationKtorUseCase
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val getVacancyInformationUseCase: GetVacancyInformationKtorUseCase,
    private val contactId : Int
) : ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO) {
            when (val response = getVacancyInformationUseCase(contactId)) {
                is CustomResult.Failure -> {
                    contactsState.value = ScreenState.Failure(response.code, response.message)
                }
                is CustomResult.Success<VacancyResponse> -> {
                    _informationList.value = response.data
                    contactsState.value = ScreenState.Success(response.data)
                }
            }
//            val contactInformation = getInformation(contactId).await()
//            if (contactInformation !is CustomResult.Failure) {
//                contactsState.value = ScreenState.Success((contactInformation as CustomResult.Success).data)
//
//            }
        }
    }
    val contactsState: MutableStateFlow<ScreenState<VacancyResponse>> =
        MutableStateFlow(ScreenState.Loading)
    private val _informationList: MutableStateFlow<VacancyResponse?> =
        MutableStateFlow(null)
    var informationList = _informationList.asStateFlow()
    suspend fun getInformation(id: Int) : Deferred<CustomResult<VacancyResponse>> {
        return coroutineScope { async(Dispatchers.IO) { getVacancyInformationUseCase(id) } }
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