package com.example.ozonchick_new_version.Screens.Otkliks

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozonchick_new_version.Data_Layer.data.db.userSharedId
import com.example.ozonchick_new_version.Data_Layer.model.DataState
import com.example.ozonchick_new_version.Data_Layer.model.TestModel_Get
import com.example.ozonchick_new_version.Data_Layer.model.User_information
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_contacts
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_otklik
import com.example.ozonchick_new_version.Data_Layer.network.supabase
import com.example.ozonchick_new_version.Domain.postGetContacts
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresListDataFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class Spisok_ViewModel() : ViewModel() {
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)

    init {
        viewModelScope.launch {
            GetInformation()
            delay(1000)
            GetUserId()
        }
    }

    /*    init {
            viewModelScope.launch {
                spisokVakans = GetInformation()
            }
        }*/
    /*    sealed class State {
            object Loading: State()
            data class Data(val data: String): State()
        }
        private var _state = MutableStateFlow<State>(State.Loading)
        val state = _state.asStateFlow()
        init {
            viewModelScope.launch {
    *//*            while (isActive) {
                val data = makeDataLoadCallOnEntry()
                _state.value = State.Data(data)
                // wait one minute and repeat your request
                delay(60 * 1000L)
            }*//*
            GetInformation()
        }
    }*/
    fun fetchData() {
        var name: String = ""
        val listA = mutableListOf<Vakans_information>()
        response.value = DataState.Loading
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val result = supabase.from("vakans").select(
                    Columns.list(
                        "vakans_name",
                        "vakans_price",
                        "vakans_organization",
                        "vakans_location",
                        "vakans_experience",
                        "vakans_mail"
                    )
                ).decodeSingle<Vakans_information>()
                listA.add(result)

                /*name = spisokVakans[0].vakans_name*/
            }
        }
        response.value = DataState.Success(listA)
    }
    private var _spisokVakans: MutableStateFlow<List<Vakans_information>> = MutableStateFlow(emptyList())
    var spisokVakans = _spisokVakans.asStateFlow()

    private val _spisokOtkliks: MutableStateFlow<List<Vakans_information>> = MutableStateFlow(emptyList())
    var spisokOtkliks = _spisokVakans.asStateFlow()

    var testPeredacha: MutableList<Vakans_otklik> = mutableListOf()
    var spisokOtvetOtkliks: MutableList<Vakans_otklik> = mutableListOf()



    var list: MutableList<Vakans_contacts> = mutableListOf()
    var number_contacts: MutableState<Int> = mutableStateOf(0)
    var email: MutableState<String> = mutableStateOf("")

    var number_phone: MutableState<String> = mutableStateOf("")
    var location: MutableState<String> = mutableStateOf("")

    var userIdfremeno: MutableState<Int> = mutableIntStateOf(0)


    /*suspend*/ fun GetInformation() {
        var name: String = ""
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
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
                ).decodeList<Vakans_information>()
                _spisokVakans.value = result

                /*name = spisokVakans[0].vakans_name*/
            }
        }
        /*delay(1000)*/
    }

    suspend fun GetSize(): Int {
        delay(1000)
        return spisokVakans.value.size
    }

    suspend fun GetMail(): String {
        delay(1000)
        return email.value
    }

    suspend fun GetContacts(mail: String, number: Int): MutableList<Vakans_contacts> {
        var listA: MutableList<Vakans_contacts> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            val result = supabase.from("vakans_contacts")
                .select()
                {
                    filter {
                        Vakans_contacts::vakans_mail eq mail
                    }
                }.decodeList<Vakans_contacts>()
            list = result.toMutableList()
            listA = result.toMutableList()
            android.util.Log.d("Number contacts", listA.toString())
            number_contacts.value = number
            email.value = mail
            delay(500)
            number_phone.value = list[0].vakans_number
            delay(500)
            location.value = list[0].vakans_raspolog
        }
        return listA
    }
     fun GetCONTACTTEST(id:Int): MutableList<Vakans_contacts> {
        var listA: MutableList<Vakans_contacts> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            val result = supabase.from("vakans_contacts")
                .select()
                {
                    filter {
                        Vakans_contacts::vakans_id eq id
                    }
                }.decodeList<Vakans_contacts>()
            list = result.toMutableList()
        }
        return listA
    }

    fun Geting(context: Context, mail: String, number: Int) {
        /*        viewModelScope.launch {
                    var responseRegistrait: Boolean
                    runBlocking {
                        responseRegistrait = postRegistrait(context).postRegistraitEmail(nameUser.value,phoneNumber.value,emailUser.value,passwordUser.value)
                    }
                    accessRegistrait.value = responseRegistrait
                }*/
        var responseGeting: TestModel_Get
        runBlocking {
            responseGeting = postGetContacts(context).postGetContact(mail, number)
        }
        list = responseGeting.list
        number_contacts = responseGeting.number

    }

    @OptIn(SupabaseExperimental::class)
    fun realTime() {
        viewModelScope.launch {
            val channel = supabase.channel("vaka")
            val users: Flow<List<Vakans_information>> = channel.postgresListDataFlow(
                schema = "public",
                table = "vakans",
                primaryKey = Vakans_information::vakans_mail
            )
            channel.subscribe()
        }

    }

    fun PushOtklik(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
/*            val result = supabase.from("vakans").select(
                Columns.list(
                    "vakans_id",
                    "vakans_name",
                    "vakans_price",
                    "vakans_organization",
                    "vakans_location",
                    "vakans_experience",
                    "vakans_mail"
                )
            ) {
                filter {
                    Vakans_information::vakans_id eq id
                }
            }.decodeList<Vakans_information>()*/
            val push = supabase.from("vakans_user_otklik").insert(
                Vakans_otklik(
                    user_long_id = userSharedId.value/*GetUserId()*/,
                    vakans_id = id,
                    otvet = "На рассмотрении"
                )
            )
        }
    }

    fun GetUserId(): Int {
        var id: MutableState<Int> = mutableIntStateOf(0)
        CoroutineScope(Dispatchers.IO).launch {
            val user = supabase.auth.currentSessionOrNull()
            val userGetId = supabase.from("user_information").select(
                Columns.list(
                    "user_id",
                    "user_name",
                    "user_phone_number",
                    "user_email"
                )
            ) {
                filter {
                    User_information::user_email eq user?.user?.email.toString()
                }
            }.decodeSingle<User_information>()

            id.value = userGetId.user_id
            userSharedId.value = userGetId.user_id
        }
        return id.value
    }

    fun GetOtkliks(userId: Int = userIdfremeno.value/*GetUserId()*/) {
        var id: MutableState<Int> = mutableIntStateOf(userId)
        var otklik_information = CoroutineScope(Dispatchers.IO).launch {
            val user = supabase.auth.retrieveUserForCurrentSession(updateSession = true)
            val userGetId = supabase.from("vakans_user_otklik").select(
                Columns.list(
                    "user_id",
                    "vakans_id",
                    "otvet",
                )
            ) {
                filter {
                    Vakans_otklik::/*user_id*/user_long_id eq id
                }
            }.decodeList<Vakans_otklik>()
        }
    }
   suspend fun GetOtvetOtkliks(number: Int):String
    {
        var otvet: MutableState<String> = mutableStateOf("")
        CoroutineScope(Dispatchers.IO).launch {
            var vakans_otvet = supabase.from("vakans_user_otklik").select(Columns.list(/*"user_id"*/"vakans_id", "otvet", "user_long_id")){
                filter {
                    Vakans_otklik::vakans_id isIn listOf(number)
                }
            }.decodeList<Vakans_otklik>()
            if (vakans_otvet.isEmpty())
            {
                otvet.value = "Ноль"
            }
            else
            {
                otvet.value = vakans_otvet[0].otvet
                spisokOtvetOtkliks = vakans_otvet.toMutableList()
            }

        }
        return otvet.value
    }
    fun InsertVakans(number: Int)
    {
        CoroutineScope(Dispatchers.IO).launch {
            var vakans_information = supabase.from("vakans").select(
                Columns.list(
                    "vakans_id",
                    "vakans_name",
                    "vakans_price",
                    "vakans_organization",
                    "vakans_location",
                    "vakans_experience",
                    "vakans_mail"
                )
            )
            {
                filter {
                    Vakans_information::vakans_id eq testPeredacha[number].vakans_id
                }
            }.decodeSingle<Vakans_information>()
            _spisokOtkliks.value = _spisokOtkliks.value.toMutableList().apply { add(vakans_information) }
//            spisokOtkliks.add(vakans_information)
        }
    }

    fun GetVakans(userId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
/*            val user = supabase.auth.retrieveUserForCurrentSession(updateSession = true)*/
            var vakans_id = supabase.from("vakans_user_otklik").select(
                Columns.list(
                    "vakans_id",
                    "otvet",
                    "user_long_id"
                )
            ) {
                filter {
                    Vakans_otklik::/*user_id*/user_long_id isIn listOf(userId)
                }
/*                filter {
                    Vakans_otklik::user_id eq userId
                }*/
            }.decodeList<Vakans_otklik>()
            testPeredacha = vakans_id.toMutableList()
//            spisokOtkliks = vakans_id.toMutableList()
//            var size:MutableState<Int> = mutableIntStateOf(vakans_id.size)
/*            var vakans_information = supabase.from("vakans").select(
                Columns.list(
                    "vakans_id",
                    "vakans_name",
                    "vakans_price",
                    "vakans_organization",
                    "vakans_location",
                    "vakans_experience",
                    "vakans_mail"
                )
            )
            {
                filter {
                    Vakans_information::vakans_id eq vakans_id.vakans_id
                }
            }.decodeList<Vakans_information>()
            spisokOtkliks = vakans_information.toMutableList()*/
        }
    }
}

