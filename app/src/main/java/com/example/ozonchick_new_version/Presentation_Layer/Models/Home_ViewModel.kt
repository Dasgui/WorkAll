package com.example.ozonchick_new_version.Presentation_Layer.Models

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.db.accessSharedAuth
import com.example.ozonchick_new_version.Data_Layer.data.db.accessSharedRegistrait
import com.example.ozonchick_new_version.Data_Layer.data.db.emailSharedUser
import com.example.ozonchick_new_version.Data_Layer.data.db.userAuthInformation
import com.example.ozonchick_new_version.Data_Layer.data.db.userSharedId
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.User_balance
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.User_id_name_email
import com.example.ozonchick_new_version.Data_Layer.model.SignUp_LogIn_Model.User_name_email
import com.example.ozonchick_new_version.Data_Layer.model.State_Test
import com.example.ozonchick_new_version.Data_Layer.model.UserState
import com.example.ozonchick_new_version.Data_Layer.network.supabase
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.LogIn_Screen
import com.google.gson.Gson
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.paperdb.Paper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class Home_ViewModel() : ViewModel() {
    val _userState = mutableStateOf<UserState>(UserState.Loading)
    var idUser: MutableState<Int> = mutableStateOf(0)
    var nameUser: MutableState<String> = mutableStateOf("")
    var realData = mutableStateOf("")
    var emailUser: MutableState<String> =
        if (emailSharedUser.value.isNotEmpty()) {
            mutableStateOf(emailSharedUser.value)
        } else {
            mutableStateOf(userAuthInformation[0].email)
        }
    var userBalance: MutableState<Int> = mutableStateOf(0)

    fun Name_for_base(): String {
        val session = supabase.auth.currentSessionOrNull()
/*        Log.d("Сессия", session?.user?.email.toString())*/
        CoroutineScope(Dispatchers.IO).launch {
            val result =
                supabase.from("user_information").select(columns = Columns.list("user_name", "user_email")) {
                    filter {
                        User_name_email::user_email eq session?.user?.email/*emailUser.value*/
                    }
                }
            val note = Gson().fromJson(result.data, Array<User_id_name_email>::class.java)
            nameUser.value = note[0].user_name
        }
        return nameUser.value
    }

/*    fun GetId(): Int {
        CoroutineScope(Dispatchers.IO).launch {
            val take_id = supabase.from("user_information").select(Columns.list("user_id", "email"))
            {
                filter {
                    user_id_email::email eq emailUser.value
                }
            }
            val note = Gson().fromJson(take_id.data, Array<user_Name>::class.java)
            idUser.value = note[0].user_id
        }
        return idUser.value
    }*/


    fun Balance_for_base(): Int {
        CoroutineScope(Dispatchers.IO).launch {
            val result =
                supabase.from("user_information").select(Columns.list("user_id", "email")) {
                    filter {
                        User_name_email::user_email eq emailUser.value
                    }
                }
            val note = Gson().fromJson(result.data, Array<User_id_name_email>::class.java)
            Log.d("База", note[0].user_id.toString())
            val result_id = supabase.from("user_balance").select() {
                filter {
                    User_balance::user_id eq note[0].user_id
                }
            }
            val note_id = Gson().fromJson(result_id.data, Array<User_balance>::class.java)
            Log.d("id с базы", note_id[0].balance.toString())
            userBalance.value = note_id[0].balance
        }
        return userBalance.value
    }

    fun Exit(navigator: Navigator?)
    {
        CoroutineScope(Dispatchers.IO).launch {
            userSharedId.value = 0
            accessSharedAuth.value = false
            accessSharedRegistrait.value = false
            userAuthInformation.clear()
            supabase.auth.signOut()
        }
        Paper.book().delete("User")
        navigator?.replaceAll(LogIn_Screen())
    }

}