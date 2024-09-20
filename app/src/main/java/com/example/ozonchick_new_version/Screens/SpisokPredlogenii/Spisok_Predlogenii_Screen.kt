package com.example.ozonchick_new_version.Screens.SpisokPredlogenii

import android.annotation.SuppressLint
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Card
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Loading
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Scaffold
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import com.example.ozonchick_new_version.Screens.Otkliks.Spisok_ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import androidx.compose.ui.text.TextStyle
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.Screens.Contacts.Contacts_Screen

var users : List<Vakans_information> = emptyList()
var idNumber : MutableState<Int> = mutableIntStateOf(0)
var isLoading : MutableState<Boolean> = mutableStateOf(false)

private var FirstMainTextStyle : TextStyle = Tema.Text_24_24_Bold
private var SecondMainTextStyle : TextStyle = Tema.Text_18_18_Medium

class Spisok_Predlogenii_Screen() : Screen {
    @Composable
    override fun Content() {
        Ozonchick_new_versionTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Spisok_Predlogenii_construct()
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Spisok_Predlogenii_construct(vm: Spisok_ViewModel = koinViewModel()) {

    LaunchedEffect(key1 = true) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            users = vm.spisokVakans.value
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
fun Main_Screen_Predlojen(loading: Boolean) {
    Public_Scaffold(mainText = "Вакансии", bottomButton = true) {
        if (loading) {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 70.dp, bottom = 80.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(idNumber.value) { item ->
                    Card_Vakans(users, item)
                }

            }
        } else {
            Public_Loading(isLoading = loading)
        }
    }
}

@Composable
fun Card_Vakans(
    list: List<Vakans_information>,
    number: Int
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
                MainText(list, number)
                MainButtons(list = list, number = number, navigator = navigator)
            }
        }
    }
}

@Composable
private fun MainText(
    list: List<Vakans_information>,
    number: Int
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
            color = MaterialTheme.colorScheme.primary
        )
        //Заработная плата Price
        Text(
            text = list[number].vakans_price/*"90 000 - 130 000"*/,
            style = FirstMainTextStyle,
            color = MaterialTheme.colorScheme.primary
        )
        //Организация Organization
        Text(
            text = list[number].vakans_organization/*"ООО Некронтиры"*/,
            style = SecondMainTextStyle,
            color = MaterialTheme.colorScheme.primary
        )
        //Расположение Location
        Text(
            text = list[number].vakans_location/*"Челябинск"*/,
            style = SecondMainTextStyle,
            color = MaterialTheme.colorScheme.primary
        )
        //Опыт работы Experience
        Text(
            text = list[number].vakans_experience/*"Опыт работы 3 до 6 лет"*/,
            style = SecondMainTextStyle,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun MainButtons(
    vm: Spisok_ViewModel = koinViewModel(),
    list: List<Vakans_information>,
    number: Int,
    navigator: Navigator?,
) {
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