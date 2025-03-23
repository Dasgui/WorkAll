package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.isLocalVersion
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Presentation.Screens.ContactsScreen
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor

@Composable
fun ResponseVacancyCard(
    list: VakansInformation,
    status: String
) {
    val navigator = LocalNavigator.current;
    CustomCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                MainText(list)
                MainButtons(
                    status = status,
                    onNavigateForContacts = {
                        navigator?.push(ContactsScreen(list.vakans_id))
                    })
            }
        }
    }
}

@Composable
private fun MainText(
    list: VakansInformation
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        //Требуется специалист Name
        Text(
            text = list.vakans_name/*"Инженер"*/,
            style = Tema.Text_24_24_Bold,
            color = MainColor
        )
        //Заработная плата Price
        Text(
            text = list.vakans_price/*"90 000 - 130 000"*/,
            style = Tema.Text_24_24_Bold,
            color = MainColor
        )
        //Организация Organization
        Text(
            text = list.vakans_organization/*"ООО Некронтиры"*/,
            style = Tema.Text_20_20_Medium,
            color = MainColor
        )
        //Расположение Location
        Text(
            text = list.vakans_location/*"Челябинск"*/,
            style = Tema.Text_20_20_Medium,
            color = MainColor
        )
        //Опыт работы Experience
        Text(
            text = "Опыт работы: " + list.vakans_experience.lowercase()/*"Опыт работы 3 до 6 лет"*/,
            style = Tema.Text_20_20_Medium,
            color = MainColor
        )
    }
}

@Composable
private fun MainButtons(
    status : String,
    onNavigateForContacts: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
    ) {
        if (publicUserId.type is UserType.Candidate || isLocalVersion == true) {
            Public_Button(
                fillButton = false,
                onClick = { /*viewModel.pushResponse(list.vakans_id)*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = status,
                    style = Tema.Text_20_20_Medium,
                )
            }
            VerticalSpacer(5.dp)
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
                style = Tema.Text_20_20_Medium,
            )
        }
    }
}