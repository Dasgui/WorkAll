package com.example.ozonchick_new_version.Screens.Responses

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Data_Layer.data.db.idSharedUser
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_otklik
import com.example.ozonchick_new_version.Data_Layer.network.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ResponsesViewModel() : ViewModel() {
    var isLoading: MutableState<Boolean> = mutableStateOf(true)

    var limit: MutableState<Long> = mutableStateOf(3)
    var offset: MutableState<Long> = mutableStateOf(0)

    private var _listVacancy: MutableStateFlow<List<Vakans_information>> = MutableStateFlow(emptyList())
    var listVacancy = _listVacancy.asStateFlow()

    private val _listResponses: MutableStateFlow<List<Vakans_information>> = MutableStateFlow(emptyList())
    var listResponses = _listResponses.asStateFlow()

    private val _idVacancy : MutableStateFlow<List<Int>> = MutableStateFlow(emptyList());
    var idVacancy = _idVacancy.asStateFlow()


    suspend fun getInformation() {
        val result = supabase.from("vakans").select(
            Columns.list(
                "vakans_id",
                "vakans_name",
                "vakans_price",
                "vakans_organization",
                "vakans_location",
                "vakans_experience",
                "vakans_mail",
                "employer_id"
            )
        ) {
            limit.value += offset.value
            limit(limit.value)
        }.decodeList<Vakans_information>()
        _listVacancy.value = result
    }

    fun pushResponse(id: Int) {
        viewModelScope.launch {
            supabase.from("vakans_user_otklik").insert(
                Vakans_otklik(
                    user_long_id = idSharedUser.value/*GetUserId()*/,
                    vakans_id = id,
                    otvet = "На рассмотрении"
                )
            )
        }
    }

    suspend fun getRespones(userId: Int) {
        val vacancyId = supabase.from("vakans_user_otklik").select(
            Columns.list(
                "vakans_id",
                "otvet",
                "user_long_id"
            )
        ) {
            filter {
                Vakans_otklik::user_long_id isIn listOf(userId)
            }
        }.decodeList<Vakans_otklik>()
        val currentIds = _idVacancy.value.toMutableList()
        for (vacancy in vacancyId) {
            currentIds.add(vacancy.vakans_id)
        }
        _idVacancy.value = currentIds;
        Log.d("Отклики", _idVacancy.value.toString());
    }

    suspend fun getVacancy(number: List<Int>)
    {
        for (i in 0 until idVacancy.value.size) {
            val vacancyInformation = supabase.from("vakans").select(
                Columns.list(
                    "vakans_id",
                    "vakans_name",
                    "vakans_price",
                    "vakans_organization",
                    "vakans_location",
                    "vakans_experience",
                    "vakans_mail",
                    "employer_id"
                )
            )
            {
                filter {
                    Vakans_information::vakans_id eq number[i]
                }
            }.decodeList<Vakans_information>()
            _listResponses.value += vacancyInformation
        }
        Log.d("Вывод вакансии из цикла", _listResponses.value.toString())
    }
    fun switchIsLoading() {
        isLoading.value = true
    }

    fun clearResponses() {
        _listResponses.value = emptyList()
    }

    fun clearIdVacancy() {
        _idVacancy.value = emptyList()
    }
}

