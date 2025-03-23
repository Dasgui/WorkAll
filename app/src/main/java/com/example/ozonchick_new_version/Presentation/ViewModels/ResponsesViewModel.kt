package com.example.ozonchick_new_version.Presentation.ViewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.isLocalVacanciesCandidateList
import com.example.ozonchick_new_version.Common.isLocalVersion
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Data.model.Vakans_otklik
import com.example.ozonchick_new_version.Domain.GetResponsesIdVacanciesUseCase
import com.example.ozonchick_new_version.Domain.GetResponsesVacanciesUseCase
import com.example.ozonchick_new_version.Presentation.Models.ResponsesUIModel
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ResponsesViewModel(
    private val getResponsesIdVacanciesUseCase: GetResponsesIdVacanciesUseCase,
    private val getResponsesVacanciesUseCase: GetResponsesVacanciesUseCase
) : ViewModel() {

    val localListVacancies = listOf<VakansInformation>(
        VakansInformation(
            vakans_id = 1,
            vakans_name = "Дизайнер",
            vakans_price = "100000",
            vakans_organization = "\"ООО\" Создатели",
            vakans_location = "Челябинск",
            vakans_experience = "Без опыта",
            vakans_mail = "gdem@inbox.ru",
            employer_id = 0
        ),
        VakansInformation(
            vakans_id = 4,
            vakans_name = "Тестировщик",
            vakans_price = "40000",
            vakans_organization = "\"ООО\" Wakfu",
            vakans_location = "Екатеренбург",
            vakans_experience = "3-5 лет",
            vakans_mail = "gdem@inbox.ru",
            employer_id = 0
        ),
    )
    val responseState : MutableStateFlow<ScreenState<ResponsesUIModel>> = MutableStateFlow(ScreenState.Loading)
    var listResponses : MutableState<List<VakansInformation>> = mutableStateOf(listOf())
//    var isLoading: MutableState<Boolean> = mutableStateOf(true)
//
//    var limit: MutableState<Long> = mutableStateOf(3)
//    var offset: MutableState<Long> = mutableStateOf(0)
//
//    private var _listVacancy: MutableStateFlow<List<Vakans_information>> =
//        MutableStateFlow(emptyList())
//    var listVacancy = _listVacancy.asStateFlow()
//
//    private val _listResponses: MutableStateFlow<List<Vakans_information>> =
//        MutableStateFlow(emptyList())
//    var listResponses = _listResponses.asStateFlow()
//
//    private val _idVacancy : MutableStateFlow<List<Int>> = MutableStateFlow(emptyList());
//    var idVacancy = _idVacancy.asStateFlow()
//
    init {
        if (isLocalVersion == true) {
            localLoadData()
        } else {
            loadData()
        }
    }
    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val idVacancies = getIdVacancies(publicUserId).await()
            Log.d("Вакансии с откликом", idVacancies.toString())
            if (idVacancies !is CustomResult.Failure) {
                val vacancies = getInformation((idVacancies as CustomResult.Success).data.map { it.vakans_id }).await()
                Log.d("Вакансии с откликом", vacancies.toString())
                if (vacancies !is CustomResult.Failure) {
                    responseState.value = ScreenState.Success(ResponsesUIModel(
                        information = (vacancies as CustomResult.Success).data,
                        status = idVacancies.data
                    ))
                    listResponses.value = vacancies.data
                } else {
                    responseState.value = ScreenState.Failure(vacancies.code, vacancies.message)
                }
                Log.d("Получение на откликнувшиеся вакансии", responseState.value.toString())
            } else {
                responseState.value = ScreenState.Failure(idVacancies.code, idVacancies.message)
            }
        }
    }

    fun localLoadData() {
        val vacancies = isLocalVacanciesCandidateList

    }

    suspend fun getIdVacancies(userId : UserId) : Deferred<CustomResult<List<Vakans_otklik>>> {
        return coroutineScope { async(Dispatchers.IO) { getResponsesIdVacanciesUseCase(userId) }}
    }

    suspend fun getInformation(idList : List<Int>) : Deferred<CustomResult<List<VakansInformation>>> {
        return coroutineScope { async(Dispatchers.IO) { getResponsesVacanciesUseCase(idList) }}
    }

//    fun pushResponse(id: Int) {
//        viewModelScope.launch {
//            supabase.from("vakans_user_otklik").insert(
//                Vakans_otklik(
//                    user_long_id = idSharedUser.value/*GetUserId()*/,
//                    vakans_id = id,
//                    otvet = "На рассмотрении"
//                )
//            )
//        }
//    }

//    suspend fun getRespones(userId: Int) {
//        val vacancyId = supabase.from("vakans_user_otklik").select(
//            Columns.Companion.list(
//                "vakans_id",
//                "otvet",
//                "user_long_id"
//            )
//        ) {
//            filter {
//                Vakans_otklik::user_long_id isIn listOf(userId)
//            }
//        }.decodeList<Vakans_otklik>()
//        val currentIds = _idVacancy.value.toMutableList()
//        for (vacancy in vacancyId) {
//            currentIds.add(vacancy.vakans_id)
//        }
//        _idVacancy.value = currentIds;
//        Log.d("Отклики", _idVacancy.value.toString());
//    }

//    suspend fun getVacancy(number: List<Int>)
//    {
//        for (i in 0 until idVacancy.value.size) {
//            val vacancyInformation = supabase.from("vakans").select(
//                Columns.Companion.list(
//                    "vakans_id",
//                    "vakans_name",
//                    "vakans_price",
//                    "vakans_organization",
//                    "vakans_location",
//                    "vakans_experience",
//                    "vakans_mail",
//                    "employer_id"
//                )
//            )
//            {
//                filter {
//                    Vakans_information::vakans_id eq number[i]
//                }
//            }.decodeList<Vakans_information>()
//            _listResponses.value += vacancyInformation
//        }
//        Log.d("Вывод вакансии из цикла", _listResponses.value.toString())
//    }
}