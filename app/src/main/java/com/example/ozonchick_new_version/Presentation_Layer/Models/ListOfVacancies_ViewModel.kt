package com.example.ozonchick_new_version.Presentation_Layer.Models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_contacts
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import com.example.ozonchick_new_version.Data_Layer.network.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListOfVacancies_ViewModel:ViewModel() {
    var spisokVakans: MutableList<Vakans_information> = mutableListOf()
    var countOfVakans: Int = 0


    fun GetInformation(): MutableList<Vakans_information> {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val result = supabase.from("vakans").select()
                {
                    filter {
                        Vakans_information::employer_id eq 9
                    }
                }.decodeList<Vakans_information>()
                spisokVakans = result.toMutableList()
                countOfVakans = spisokVakans.size
                /*name = spisokVakans[0].vakans_name*/
            }
        }

        /*delay(1000)*/
        return spisokVakans
    }
}