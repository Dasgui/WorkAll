package com.example.ozonchick_new_version.Screens.Contacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Scaffold
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.mainColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_contacts
import com.example.ozonchick_new_version.Screens.Otkliks.Spisok_ViewModel
import com.example.ozonchick_new_version.R
import org.koin.androidx.compose.koinViewModel

private var mainTextStyle: TextStyle = Tema.Text_24_24_Medium
private var paddimgBetween_Text = 10.dp


class Contacts_Screen() : Screen {
    @Composable
    @Preview
    override fun Content() {
        Ozonchick_new_versionTheme {
            Contacts_construct()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Contacts_construct(vm: Spisok_ViewModel = koinViewModel(), /*list:MutableList<Vakans_contacts>*/) {
/*    LaunchedEffect(true) {
        vm.GetContacts(vm.email.value, vm.number_contacts.value)
        delay(1000)
        Log.d("LaunchProverka", vm.email.value)
    }*/
    var mutableList by remember { mutableStateOf(mutableListOf<Vakans_contacts>()) }
    /*var list:MutableList<Vakans_contacts> = mutableListOf()*/
    var list: MutableList<Vakans_contacts> = mutableListOf()
    var number: MutableState<String> = remember {
        mutableStateOf("")
    }
    var email: MutableState<String> = remember {
        mutableStateOf("")
    }
    var location: MutableState<String> = remember {
        mutableStateOf("")
    }

/*    LaunchedEffect(Unit) {
        list = vm.list
        Log.d("Contacts Screen", list.toString())
    }*/
/*    runBlocking {
        delay(1000)
        list = vm.list
        Log.d("Contacts Screen", list.toString())
    }*/
    val navigator = LocalNavigator.current
    /*Log.d("Contacts Next", list.toString())*/
    Public_Scaffold(
        mainText = "Контакты",
        backButton = true,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_square_right),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {

                                    navigator?.pop()
                                },
                            tint = mainColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, start = 5.dp, end = 5.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {


            item {
                LaunchedEffect(true) {
                    email.value = vm.GetMail()
                }
                Text(
                    modifier = Modifier
                        .padding(top = paddimgBetween_Text),
                    text = "Номер телефона: ${vm.list[0].vakans_number}"/*"Номер телефона: ${vm.number_phone.value}"*/, /*"Номер телефона: +79000"*/ /*vm.list[vm.number_contacts.value].vakans_mail*/
                    style = mainTextStyle,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .padding(top = paddimgBetween_Text),
                    text = /*list[0].vakans_mail*/ /*"Почта: polina@mail.ru"*/ /*"Почта: ${vm.spisokVakans[vm.number_contacts.value].vakans_mail}"*/ "Почта: ${vm.list[0].vakans_mail}",
                    style = mainTextStyle,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .padding(top = paddimgBetween_Text),
                    text = /*list[0].vakans_raspolog*/ /*"Расположение: Захарево 89"*/ /*"Расположение: ${vm.location.value}"*/ "Расположение: ${vm.list[0].vakans_raspolog}",
                    style = mainTextStyle,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .padding(top = paddimgBetween_Text),
                    text = "О работе:",
                    style = mainTextStyle,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier
                        .padding(top = paddimgBetween_Text),
                    text = vm.list[0].vakans_aboutWork,
                    style = mainTextStyle,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}