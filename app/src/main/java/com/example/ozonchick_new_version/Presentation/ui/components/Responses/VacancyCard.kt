package com.example.ozonchick_new_version.Presentation.ui.components.Responses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyCreateInfo
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyInformation
import com.example.ozonchick_new_version.Presentation.ui.components.Public_Button
import com.example.ozonchick_new_version.Presentation.ui.components.CustomCard
import com.example.ozonchick_new_version.Presentation.ui.components.RowTextImage
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.EnabledColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SwitchOffColor
import com.example.ozonchick_new_version.R

@Composable
fun VacancyCard(
    list: VacancyInformation,
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
            }
        }
    }
}

@Composable
private fun MainText(
    list: VacancyInformation
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        //Требуется специалист Name
        Text(
            text = list.name/*"Инженер"*/,
            style = Tema.Text_24_24_Bold,
            color = MainColor
        )
        RowTextImage(R.drawable.wallet_fill_icon, list.price.toString())

        RowTextImage(R.drawable.organization_icon, list.employee.nameOrganization)
        RowTextImage(R.drawable.town_icon, list.location)


        RowTextImage(R.drawable.experience_icon, "Опыт работы: " + list.experience.name)
    }
}


@Composable
fun VacancyCard(
    list: VacancyCreateInfo,
    buttons: @Composable () -> Unit,
    colorBorder: Color = MainColor,
    textColor : Color = MainColor,
    backGroundColor : Color = BackgroundColor,
    mainTextVisible : Boolean
) {
    val navigator = LocalNavigator.current;
    var visible by remember { mutableStateOf(true) }
    if (mainTextVisible == false) {
        visible = false
    }
    CustomCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .clickable {
                visible = !visible
            },
        colorBorder = colorBorder,
        backGroundColor = backGroundColor
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
                MainText(list, textColor, mainTextVisible)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    if (mainTextVisible == false) {
                        Text(
                            text = "Вакансия скрыта",
                            style = Tema.Text_20_20_Medium,
                            textAlign = TextAlign.Center,
                            color = MainColor
                        )
                    }

                    if (visible == true) {
                        buttons()
                    }


                }
            }
        }
    }
}

@Composable
private fun MainText(
    list: VacancyCreateInfo,
    textColor : Color = MainColor,
    mainTextVisible : Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        //Требуется специалист Name
        Text(
            text = list.name/*"Инженер"*/,
            style = Tema.Text_24_24_Bold,
            color = textColor
        )
        if (mainTextVisible == true) {
            RowTextImage(R.drawable.wallet_fill_icon, list.price.toString(), textColor = textColor)

            RowTextImage(R.drawable.organization_icon, localUserInformation.name, textColor = textColor)
            RowTextImage(R.drawable.town_icon, list.location, textColor = textColor)


            RowTextImage(R.drawable.experience_icon, "Опыт работы: " + list.experience.name, textColor = textColor)
        }
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
        if (localUserInformation.type is UserType.Candidate) {
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