package com.example.ozonchick_new_version.Screens.Home

import android.annotation.SuppressLint
import android.graphics.Paint.Style
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Card
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Loading
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Scaffold
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.BackColorBlue
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.BackColorGreen
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Background
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_MainText
import com.example.ozonchick_new_version.Data_Layer.model.DataState
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_contacts
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import com.example.ozonchick_new_version.Presentation_Layer.Models.Home_ViewModel
import com.example.ozonchick_new_version.Presentation_Layer.Models.Spisok_ViewModel
import com.example.ozonchick_new_version.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.koinViewModel
import java.time.format.TextStyle

private var FirstMainTextStyle: androidx.compose.ui.text.TextStyle = Tema.Text_24_24_Bold
private var SecondMainTextStyle: androidx.compose.ui.text.TextStyle = Tema.Text_18_18_Medium
private var mainTextColor: MutableState<Color> = Color_MainText
private var background: MutableState<Color> = Color_Background
var users: MutableList<Vakans_information> = mutableListOf()
var idNumber: MutableState<Int> = mutableIntStateOf(0)
class Spisok_Predlogenii_Screen() : Screen {
    @Composable
    override fun Content() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color_Background.value
        ) {
            Spisok_Predlogenii_construct()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Spisok_Predlogenii_construct(vm: Spisok_ViewModel = koinViewModel()) {
//    var mutableList by remember { mutableStateOf(mutableListOf<Vakans_information>()) }
//    var data by remember { mutableStateOf("") }
    var isLoading: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true) {
//        runBlocking {
//
//            users = vm.spisokVakans
//            /*idNumber.value = users.size*/
//        }
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            users = vm.spisokVakans
            delay(1000)
            vm.GetInformation()
            Log.d("Тетс", vm.spisokVakans.toString())
            delay(1000)
            idNumber.value = vm.GetSize()
            delay(1000)
            isLoading.value = true
        }

    }
    Main_Screen_Predlojen(loading = isLoading.value)


}

@Composable
fun Main_Screen_Predlojen(vm: Spisok_ViewModel = koinViewModel(), loading:Boolean)
{
    Public_Scaffold(mainText = "Вакансии", bottomButton = true) {
        if (loading)
        {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 70.dp, bottom = 80.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(idNumber.value) { item ->
                    Card_Vakans(item, users, vm)
                    Log.d("RealTime", vm.realbd.value)
                }

            }
        }
        else
        {
            Public_Loading(isLoading = loading)
        }
    }
}

@Composable
fun Card_Vakans(/*list: MutableList<Vakans_information>,*/ number: Int,
                list: MutableList<Vakans_information>,
                vm: Spisok_ViewModel
) {
    var localNumber: MutableState<Int> = remember {
        mutableIntStateOf(number)
    }
    val context = LocalContext.current
    var contactList: MutableList<Vakans_contacts> = mutableListOf()
    val navigator = LocalNavigator.current
    Public_Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(bottom = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 5.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    //Требуется специалист Name
                    Text(
                        text = list[number].vakans_name/*"Инженер"*/,
                        style = FirstMainTextStyle,
                        color = mainTextColor.value
                    )
                    //Заработная плата Price
                    Text(
                        text = list[number].vakans_price/*"90 000 - 130 000"*/,
                        style = FirstMainTextStyle,
                        color = mainTextColor.value
                    )
                    //Организация Organization
                    Text(
                        text = list[number].vakans_organization/*"ООО Некронтиры"*/,
                        style = SecondMainTextStyle,
                        color = mainTextColor.value
                    )
                    //Расположение Location
                    Text(
                        text = list[number].vakans_location/*"Челябинск"*/,
                        style = SecondMainTextStyle,
                        color = mainTextColor.value
                    )
                    //Опыт работы Experience
                    Text(
                        text = list[number].vakans_experience/*"Опыт работы 3 до 6 лет"*/,
                        style = SecondMainTextStyle,
                        color = mainTextColor.value
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Public_Button(
                        fillButton = false,
                        onClick = { vm.PushOtklik(list[number].vakans_id) },
                        modifier = Modifier.width(180.dp)
                    ) {
                        Text(
                            text = "Откликнуться",
                            style = SecondMainTextStyle,
                        )
                    }
                    Public_Button(
                        fillButton = true,
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                delay(500)
                                vm.GetCONTACTTEST(list[number].vakans_id)
                                delay(500)
  /*                              vm.GetContacts(
                                    vm.spisokVakans[localNumber.value].vakans_mail,
                                    number
                                )
                                delay(500)
                                Log.d(
                                    "Spisok Contacts mail",
                                    vm.spisokVakans[localNumber.value].vakans_mail
                                )
                                Log.d("Spisok Contacts number", number.toString())
                                vm.number_contacts.value = localNumber.value
                                delay(500)
                                Log.d("Spisok Contacts", vm.list.toString())
                                delay(500)
                                Log.d("Spisok Phone", vm.number_phone.value)*/
                                navigator?.push(Contacts_Screen())
                            }

                        },
                        modifier = Modifier.width(180.dp)
                    ) {
                        Text(
                            text = "Подробнее",
                            style = SecondMainTextStyle,
                        )
                    }
                }
            }
        }


    }
}