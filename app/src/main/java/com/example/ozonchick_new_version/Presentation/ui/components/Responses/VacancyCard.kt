package com.example.ozonchick_new_version.Presentation.ui.components.Responses

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Presentation.Screens.ContactsScreen
import com.example.ozonchick_new_version.Presentation.Screens.Responses.ResponsesViewModel
import com.example.ozonchick_new_version.Presentation.ui.components.Public_Button
import com.example.ozonchick_new_version.Presentation.ui.components.CustomCard
import com.example.ozonchick_new_version.Presentation.ui.components.RowTextImage
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun VacancyCard(
    list: VakansInformation,
    buttons: @Composable () -> Unit
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    buttons()
                }
//                MainButtons(
//                    onNavigateForContacts = {
//                        navigator?.push(ContactsScreen(list.vakans_id))
//                    },
//                    addVacancy = {
//                        addVacancy()
//                    }
//                )
            }
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
        RowTextImage(R.drawable.wallet_fill_icon, list.vakans_price)
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(10.dp)
//        ) {
//            Icon(
//                painter = painterResource(R.drawable.wallet_3),
//                contentDescription = null,
//                modifier = Modifier.size(30.dp),
//                tint = MainColor
//            )
//            //Заработная плата Price
//            Text(
//                text = list.vakans_price/*"90 000 - 130 000"*/,
//                style = Tema.Text_24_24_Bold,
//                color = MainColor
//            )
//        }
//        //Организация Organization
//        Text(
//            text = list.vakans_organization/*"ООО Некронтиры"*/,
//            style = Tema.Text_20_20_Medium,
//            color = MainColor
//        )
        RowTextImage(R.drawable.organization_icon, list.vakans_organization)
        RowTextImage(R.drawable.town_icon, list.vakans_location)
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(10.dp)
//        ) {
//            Icon(
//                painter = painterResource(R.drawable.town_icon),
//                contentDescription = null,
//                modifier = Modifier.size(30.dp),
//                tint = MainColor
//            )
//            //Расположение Location
//            Text(
//                text = list.vakans_location/*"Челябинск"*/,
//                style = Tema.Text_20_20_Medium,
//                color = MainColor
//            )
//        }

//        //Опыт работы Experience
//        Text(
//            text = "Опыт работы: " + list.vakans_experience.lowercase()/*"Опыт работы 3 до 6 лет"*/,
//            style = Tema.Text_20_20_Medium,
//            color = MainColor
//        )

        RowTextImage(R.drawable.experience_icon, "Опыт работы: " + list.vakans_experience.lowercase())
    }
}

@Composable
private fun MainButtons(
    onNavigateForContacts: () -> Unit,
    addVacancy: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (publicUserId.type is UserType.Candidate) {
            Public_Button(
                fillButton = false,
                onClick = { addVacancy() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Откликнуться",
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