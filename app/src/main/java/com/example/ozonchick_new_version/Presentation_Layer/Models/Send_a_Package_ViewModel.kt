package com.example.ozonchick_new_version.Presentation_Layer.Models

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.db.emailSharedUser

import com.example.ozonchick_new_version.Data_Layer.data.db.idUser
import com.example.ozonchick_new_version.Data_Layer.data.db.userAuthInformation
import com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model.Destination_details
import com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model.infoZakaz
import com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model.Main_details
import com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model.Package_destination
import com.example.ozonchick_new_version.Data_Layer.model.Send_a_Package_Model.Package_details
import com.example.ozonchick_new_version.Data_Layer.network.supabase
import com.example.ozonchick_new_version.Domain.postGetUserId
import com.example.ozonchick_new_version.Domain.postInsertUserDestination
import com.example.ozonchick_new_version.Screens.Send_a_Package.Send_a_Package_Receipt_Screen
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

class Send_a_Package_ViewModel() : ViewModel() {

    var emailSend = if (emailSharedUser.value.isNotEmpty()) {
        emailSharedUser.value
    } else {
        userAuthInformation[0].email
    }

    var Add: MutableState<Boolean> = mutableStateOf(true)
    var Colich: MutableState<Int> = mutableStateOf(1)
    var ListZakaz: MutableList<infoZakaz> = mutableListOf<infoZakaz>()

    var Delivery_Charges_const: Double = 2500.00
    var Instant_Delivery_const: Double = 300.00
    var Procent_Tax: Double = 5.0

    //    var Kolvo:MutableState<Int> = mutableStateOf(0)
    var chargesDelivery: Double = 0.0
    var taxDelivary: Double = 0.0
    var sumDelivary: Double = 0.0

    var chargesName: ArrayList<String> =
        arrayListOf("Delivery Charges", "Instant delivery", "Tax and Service Charges")
    var chargesZnach: ArrayList<Double> =
        arrayListOf()

    val MainText_Card: ArrayList<String> =
        arrayListOf("Address", "State,Country", "Phone number", "Others")


    var packageItem: MutableState<String> = mutableStateOf("")
    var weightItem: MutableState<String> = mutableStateOf("")
    var worthItem: MutableState<String> = mutableStateOf("")

    var address: MutableState<String> = mutableStateOf("Lenina 8")
    var stateCountry: MutableState<String> = mutableStateOf("Moscow")
    var phoneNumber: MutableState<String> = mutableStateOf("+245 643")
    var others: MutableState<String> = mutableStateOf("")

    var get_package_id: Package_destination = Package_destination(0)
    val navigator = LocalNavigator

    fun Proverka(navigator : Navigator?, context: Context)
    {
        for (i in 0 until ListZakaz.size) {
            if ((address.value.isNotEmpty() && stateCountry.value.isNotEmpty()
                && phoneNumber.value.isNotEmpty() && packageItem.value.isNotEmpty()
                && weightItem.value.isNotEmpty() && worthItem.value.isNotEmpty()
                && (ListZakaz[i].Address.isNotEmpty() && Colich.value == i + 1 )
                && (ListZakaz[i].State_Country.isNotEmpty() && Colich.value == i + 1)
                && (ListZakaz[i].PhoneNumber.isNotEmpty() && Colich.value == i + 1)) && ChekForNumber()
                && ChekForWord(weightItem.value) && ChekForWord(worthItem.value)
            ) {
                Log.d("Размер", ListZakaz.size.toString())
                GetUserId()
                ChargesChet()
                navigator?.push(Send_a_Package_Receipt_Screen())
            }
            else
            {
                val toast = Toast.makeText(context,"Введите корректные данные", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.FILL_VERTICAL,0,0)
                toast.show()
            }
        }
    }
    fun ChekForNumber(it:String = packageItem.value):Boolean
    {
        if (it.all { it.isDigit() })
        {
            return false
        }
        else
        {
            return true
        }
    }
    fun ChekForWord(it:String):Boolean
    {
        if (it.all { it.isDigit() })
        {
            return true
        }
        return false
    }



    fun GetUserId() {
        CoroutineScope(Dispatchers.IO).launch {
            if(get_package_id.package_id == 0)
            {
                get_package_id = supabase.from("main_details").insert(Main_details(idUser.value))
                {
                    select(Columns.list("package_id"))
                }.decodeSingle<Package_destination>()
            }
/*            var insert_origin = supabase.from("origin_details").insert(
                Destination_details(
                    get_package_id.package_id,
                    address.value,
                    stateCountry.value,
                    phoneNumber.value,
                    others.value
                )
            )
            var insert_package = supabase.from("package_details").insert(
                Package_details(
                    get_package_id.package_id,
                    packageItem.value,
                    weightItem.value,
                    worthItem.value
                )
            )
            for (i in 0 until Colich.value) {
                InsertDestination(i, get_package_id.package_id)
            }*/
        }
    }

    fun InsertIformation()
    {
        CoroutineScope(Dispatchers.IO).launch {
            var insert_origin = supabase.from("origin_details").insert(
                Destination_details(
                    get_package_id.package_id,
                    address.value,
                    stateCountry.value,
                    phoneNumber.value,
                    others.value
                )
            )
            var insert_package = supabase.from("package_details").insert(
                Package_details(
                    get_package_id.package_id,
                    packageItem.value,
                    weightItem.value,
                    worthItem.value
                )
            )
            for (i in 0 until Colich.value) {
                InsertDestination(i, get_package_id.package_id)
            }
        }
    }

    fun CleanViewModel() {
        var listToClean: ArrayList<String> = arrayListOf(
            packageItem.value,
            weightItem.value,
            worthItem.value,
            address.value,
            stateCountry.value,
            phoneNumber.value,
            others.value
        )
    }

    fun GetId(context: Context) {
        runBlocking {
            postGetUserId(context).postGetId(emailSend)
        }
        /*        CoroutineScope(Dispatchers.IO).launch {
                    val take_id = supabase.from("user_information").select(Columns.list("user_id", "email"))
                    {
                        filter {
                            User_information::email eq emailSend
                        }
                    }
                    var note = Gson().fromJson(take_id.data, Array<User_information>::class.java)

                    idUser.value = note[0].user_id
                }*/
        Log.d("Id User", idUser.value.toString())
    }

    fun GetPackage() {

        CoroutineScope(Dispatchers.IO).launch {
            var get_package_id = supabase.from("main_details").insert(Main_details(idUser.value))
            {
                select(Columns.list("package_id"))
            }.decodeSingle<Package_destination>()
        }
    }

    fun InsertDestination(nomer: Int, pacId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            postInsertUserDestination().postInsertDestination(pacId, ListZakaz[nomer])
        }
/*        CoroutineScope(Dispatchers.IO).launch {
            supabase.from("destination_details").insert(
                Destination_details(
                    pacId,
                    ListZakaz[nomer].Address,
                    ListZakaz[nomer].State_Country,
                    ListZakaz[nomer].PhoneNumber,
                    ListZakaz[nomer].Others,
                )
            )
        }*/


//            val insert_origin = supabase.from("destination_details").insert(
//                Destination_Details(
//                    get_package_id.package_id,
//                    ListZakaz[nomer].Address,
//                    ListZakaz[nomer].State_Country,
//                    ListZakaz[nomer].PhoneNumber,
//                    ListZakaz[nomer].Others,
//                )
//            )

//            Log.d("id", get_package_id.toString())
        /*            val insert_destination = supabase.from("destination_details").insert(
                        Destination_Details(
                            1,
                            "dd",
                            "dd",
                            "aa",
                            "ads"
        //                    get_package_id.package_id,
        //                    ListZakaz[nomer].Address,
        //                    ListZakaz[nomer].State_Country,
        //                    ListZakaz[nomer].PhoneNumber,
        //                    ListZakaz[nomer].Others,
                        )
                    )*/

//            val insert_destination = supabase.from("destination_details").insert(
//                Destination_Details(
//                    ListZakaz[nomer].Address,
//                    ListZakaz[nomer].State_Country,
//                    ListZakaz[nomer].PhoneNumber,
//                    ListZakaz[nomer].Others,
//                    package_id.decodeSingle<Int>()
//                )
//            )
//            {
///*                filter {
//                    Destination_Details::package_id eq package_id
//                }*/
//            }


//            val insert_destination = supabase.from("destination_details").insert(Destination_Details(ListZakaz[nomer].Address,
//                ListZakaz[nomer].State_Country,
//                ListZakaz[nomer].PhoneNumber,
//                ListZakaz[nomer].Others,
//                idUser.value
//                )
//            )
//            Log.d("Проверка данных", ListZakaz[nomer].toString())

    }

    fun ChargesChet() {


        var Kolvo: Int = ListZakaz.size

        chargesDelivery = Delivery_Charges_const * (Kolvo - 1)
        taxDelivary = ((Delivery_Charges_const + Instant_Delivery_const) / 100) * Procent_Tax
        sumDelivary = chargesDelivery + taxDelivary + Instant_Delivery_const
        chargesZnach =
            arrayListOf(chargesDelivery, taxDelivary, sumDelivary)
    }

}


@Serializable
data class Origin_Details(
    val user_id: Int,
    val address: String,
    val state_country: String,
    val phone_number: String,
    val others: String,
)







