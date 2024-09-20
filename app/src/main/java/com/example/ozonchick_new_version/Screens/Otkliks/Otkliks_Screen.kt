package com.example.ozonchick_new_version.Screens.Otkliks

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.db.userSharedId
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Card
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Loading
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Scaffold
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_MainText
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import com.example.ozonchick_new_version.Screens.Contacts.Contacts_Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
private var FirstMainTextStyle: androidx.compose.ui.text.TextStyle = Tema.Text_24_24_Bold
private var SecondMainTextStyle: androidx.compose.ui.text.TextStyle = Tema.Text_18_18_Medium
private var mainTextColor: MutableState<Color> = Color_MainText

class Otkliks_Screen:Screen {
    @Composable
    override fun Content() {
        Ozonchick_new_versionTheme {
            Otkliks_construct()
        }

    }
}

@Composable
fun Otkliks_construct(vm: Spisok_ViewModel = koinViewModel())
{
    var isLoading: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true) {
        delay(1000)
        vm.GetVakans(userSharedId.value)
        delay(1000)
        Log.d("GetVakans", vm.spisokOtkliks.toString())
        delay(1000)
        Log.d("SizeTestPeredach", vm.testPeredacha.size.toString())
        delay(1000)
        for (i in 0 until vm.testPeredacha.size){
            vm.InsertVakans(i)
        }
        delay(1000)
        Log.d("GetOtkliks", vm.spisokOtkliks.toString())
        delay(1000)
        vm.GetOtvetOtkliks(userSharedId.value)
        delay(1000)
        isLoading.value =true
    }
    MainScreen(isLoading = isLoading.value)

}

@Composable
fun Card_V(number: Int,
           list: StateFlow<List<Vakans_information>>,
           vm: Spisok_ViewModel
) {
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
                        text = list.collectAsState().value[number].vakans_name,
                        style = FirstMainTextStyle,
                        color = MaterialTheme.colorScheme.primary
                    )
                    //Заработная плата Price
                    Text(
                        text = list.collectAsState().value[number].vakans_price,
                        style = FirstMainTextStyle,
                        color = MaterialTheme.colorScheme.primary
                    )
                    //Организация Organization
                    Text(
                        text = list.collectAsState().value[number].vakans_organization,
                        style = SecondMainTextStyle,
                        color = MaterialTheme.colorScheme.primary
                    )
                    //Расположение Location
                    Text(
                        text = list.collectAsState().value[number].vakans_location,
                        style = SecondMainTextStyle,
                        color = MaterialTheme.colorScheme.primary
                    )
                    //Опыт работы Experience
                    Text(
                        text = list.collectAsState().value[number].vakans_experience,
                        style = SecondMainTextStyle,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Public_Button(
                        fillButton = true,
                        onClick = { vm.PushOtklik(list.value[number].vakans_id) },
                        modifier = Modifier.width(180.dp)
                    ) {
                        Text(
                            text = vm.testPeredacha[number].otvet,
                            style = SecondMainTextStyle,
                            textAlign = TextAlign.Center
                        )
                    }
                    Public_Button(
                        fillButton = false,
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                delay(500)
                                vm.GetCONTACTTEST(vm.spisokOtkliks.value[number].vakans_id)
                                delay(1000)
                                Log.d("Приём контактов", vm.list.toString())
                                navigator?.push(Contacts_Screen())
                            }


                        },
                        modifier = Modifier.width(180.dp)
                    ) {
                        Text(
                            text = "Контакты",
                            style = SecondMainTextStyle,
                        )
                    }
                }
            }
        }


    }
}



@Composable
fun MainScreen(vm: Spisok_ViewModel = koinViewModel(), isLoading:Boolean)
{
    Public_Scaffold(
        bottomButton = true,
        mainText = "Отклики"
    ) {
        if (isLoading)
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 90.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(vm.testPeredacha.size)
                {
                        item->
                    Card_V(item, vm.spisokOtkliks, vm)
                }
            }
        }
        else
        {
            Public_Loading(isLoading = isLoading)
            /*LoadingScreen()*/
        }
    }

}


//Экран загрузки
/*
Column(
modifier = Modifier.fillMaxSize().padding(top = 90.dp),
horizontalAlignment = Alignment.CenterHorizontally,
verticalArrangement = Arrangement.Center
) {
    var rorate by remember {
        mutableStateOf(0f)
    }
    var DownImage by remember {
        mutableStateOf(R.drawable.cruck)
    }
    Image(
        painter = painterResource(id = DownImage),
        contentDescription = null,
        modifier = Modifier
            .padding(top = 100.dp)
            .size(120.dp)
            .rotate(rorate),
        colorFilter = ColorFilter.tint(mainTextColor.value)
    )
    LaunchedEffect(key1 = true) {

        CoroutineScope(Dispatchers.IO).launch {
            while (!isLoading) {
                rorate = rorate + 1f
                delay(1)
            }
        }
    }
}*/
