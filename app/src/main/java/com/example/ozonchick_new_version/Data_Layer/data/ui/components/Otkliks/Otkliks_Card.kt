package com.example.ozonchick_new_version.Data_Layer.data.ui.components.Otkliks

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Card
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import com.example.ozonchick_new_version.Screens.Contacts.Contacts_Screen
import com.example.ozonchick_new_version.Screens.Otkliks.Spisok_ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private var FirstMainTextStyle: androidx.compose.ui.text.TextStyle = Tema.Text_24_24_Bold
private var SecondMainTextStyle: androidx.compose.ui.text.TextStyle = Tema.Text_18_18_Medium

@Composable
fun JobCard(
    number: Int,
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
                MainBody(number, list)
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
                                navigator?.push(Contacts_Screen(vm.spisokOtkliks.value[number].vakans_id))
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
private fun MainBody(
    number: Int,
    list: StateFlow<List<Vakans_information>>
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = list.collectAsState().value[number].vakans_name,
            style = FirstMainTextStyle,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = list.collectAsState().value[number].vakans_price,
            style = FirstMainTextStyle,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = list.collectAsState().value[number].vakans_organization,
            style = SecondMainTextStyle,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = list.collectAsState().value[number].vakans_location,
            style = SecondMainTextStyle,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = list.collectAsState().value[number].vakans_experience,
            style = SecondMainTextStyle,
            color = MaterialTheme.colorScheme.primary
        )
    }
}