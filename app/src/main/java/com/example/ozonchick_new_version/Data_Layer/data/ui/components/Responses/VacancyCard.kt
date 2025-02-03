package com.example.ozonchick_new_version.Data_Layer.data.ui.components.Responses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Button
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Public_Card
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import com.example.ozonchick_new_version.Screens.Contacts.Contacts_Screen
import com.example.ozonchick_new_version.Screens.Responses.ResponsesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun VacancyCard(
    list: Vakans_information
) {
    Public_Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(top = 20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp, vertical = 20.dp)
        ) {
            MainText(list)
        }
    }
}

@Composable
private fun MainText(
    list: Vakans_information
) {
    val navigator = LocalNavigator.current;
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        //Требуется специалист Name
        Text(
            text = list.vakans_name/*"Инженер"*/,
            style = Tema.Text_24_24_Bold,
            color = MaterialTheme.colorScheme.primary
        )
        //Заработная плата Price
        Text(
            text = list.vakans_price/*"90 000 - 130 000"*/,
            style = Tema.Text_24_24_Bold,
            color = MaterialTheme.colorScheme.primary
        )
        //Организация Organization
        Text(
            text = list.vakans_organization/*"ООО Некронтиры"*/,
            style = Tema.Text_18_18_Medium,
            color = MaterialTheme.colorScheme.primary
        )
        //Расположение Location
        Text(
            text = list.vakans_location/*"Челябинск"*/,
            style = Tema.Text_18_18_Medium,
            color = MaterialTheme.colorScheme.primary
        )
        //Опыт работы Experience
        Text(
            text = list.vakans_experience/*"Опыт работы 3 до 6 лет"*/,
            style = Tema.Text_18_18_Medium,
            color = MaterialTheme.colorScheme.primary
        )
        MainButtons(list = list,
            onNavigateForContacts = {
                navigator?.push(Contacts_Screen(list.vakans_id))
            })
    }
}

@Composable
private fun MainButtons(
    viewModel: ResponsesViewModel = koinViewModel(),
    list: Vakans_information,
    onNavigateForContacts : () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 10.dp),
    ) {
        Public_Button(
            fillButton = false,
            onClick = { viewModel.pushResponse(list.vakans_id) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Откликнуться",
                style = Tema.Text_18_18_Medium,
            )
        }
        Public_Button(
            fillButton = true,
            onClick = {
                onNavigateForContacts()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Подробнее",
                style = Tema.Text_18_18_Medium,
            )
        }
    }
}