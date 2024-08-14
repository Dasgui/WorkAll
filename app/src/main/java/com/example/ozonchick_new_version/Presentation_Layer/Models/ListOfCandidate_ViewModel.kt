package com.example.ozonchick_new_version.Presentation_Layer.Models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Data_Layer.model.Candidate_information
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information_id
import com.example.ozonchick_new_version.Data_Layer.network.supabase
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ListOfCandidate_ViewModel:ViewModel() {
    var spisokCandidates: MutableList<Candidate_information> = mutableListOf()
    var countOfCandidates: Int = 0
    var listOfVakansId: MutableList<Vakans_information_id> = mutableListOf()


    fun GetPeople(): MutableList<Vakans_information_id> {
        var okay:Boolean = false
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val result = supabase.from("vakans").select(columns = Columns.list("vakans_id, employer_id"))
                {
                    filter {
                        Vakans_information_id::employer_id eq 9
                    }
                }.decodeList<Vakans_information_id>()
                delay(1000)
                listOfVakansId.addAll(result.toMutableList())
                delay(1000)

            }
        }
        return listOfVakansId
    }

    fun GetCandidates(): MutableList<Candidate_information> {
        var listOfId:MutableList<Vakans_information_id> = mutableListOf()
        listOfId.addAll(GetPeople())
        Log.d("Hfpvth", listOfVakansId.toString())
        Log.d("asd", listOfId.toString())
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {

                delay(1000)

            }

        }
        return spisokCandidates
    }

}