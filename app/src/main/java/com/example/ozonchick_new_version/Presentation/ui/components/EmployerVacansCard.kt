package com.example.ozonchick_new_version.Presentation.ui.components

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Presentation.Screens.ContactsScreen
import com.example.ozonchick_new_version.Presentation.ViewModels.ResponsesViewModel
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import org.koin.androidx.compose.koinViewModel


@Composable
fun EmployerVacansCard(
    vakansInformation: VakansInformation
) {
    val navigator = LocalNavigator.current
    CustomCard {
        Column(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            MainText(
                vakansInformation
//                VakansInformation(
//                    vakans_id = 1,
//                    vakans_price = "200",
//                    vakans_name = "Программист",
//                    vakans_organization = "ООО Газпром",
//                    vakans_location = "Челябинск",
//                    vakans_experience = "От 3 лет",
//                    vakans_mail = "gaigo@mail.ru",
//                    employer_id = 1
//                )
            )
            MainButtons(
                onNavigateForContacts = {
                    navigator?.push(ContactsScreen(1))
                })
        }
    }
}

@Composable
private fun MainText(
    list: VakansInformation
) {
    Column(
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
    viewModel: ResponsesViewModel = koinViewModel(),
    onNavigateForContacts: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        CustomButton(
            onClick = {
                onNavigateForContacts()
            },
            placeText = "Изменить",
            modifier = Modifier.fillMaxWidth(),
            reverseColors = true
        )
        CustomButton(
            onClick = {
                onNavigateForContacts()
            },
            placeText = "Соискатели",
            modifier = Modifier.fillMaxWidth(),
            reverseColors = true
        )
        CustomButton(
            onClick = {
                onNavigateForContacts()
            },
            placeText = "Удалить",
            modifier = Modifier.fillMaxWidth()
        )
    }
}