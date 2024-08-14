package com.example.ozonchick_new_version.Data_Layer.data.ui.components.Test_Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_Background
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.Color_MainText
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.Home.Otkliks_Screen
import com.example.ozonchick_new_version.Screens.Home.Profile_Screen
import com.example.ozonchick_new_version.Screens.Home.Spisok_Predlogenii_Screen
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import androidx.compose.ui.text.TextStyle

@Composable
fun Scafold_BottomBar(
    navigator : Navigator?,
    sizeIcon : Dp = 35.dp,
    bottomTextStyle : TextStyle = Tema.Text_16_16_Medium
) {
    BottomAppBar(
        containerColor = Color_Background.value,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { navigator?.push(Spisok_Predlogenii_Screen()) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.vakansi_icon_3),
                    contentDescription = "Вакансии",
                    modifier = Modifier
                        .size(sizeIcon),
                    tint = Color_MainText.value
                )
                Text(
                    text = "Вакансии",
                    style = bottomTextStyle,
                    color = Color_MainText.value,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { navigator?.push(Otkliks_Screen()) }
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.folder),
                    contentDescription = "Отклики",
                    modifier = Modifier
                        .size(sizeIcon),
                    tint = Color_MainText.value
                )
                Text(
                    text = "Собственные вакансии"/*"Отклики"*/,
                    style = bottomTextStyle,
                    color = Color_MainText.value,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { navigator?.push(Profile_Screen()) }
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.profile_circle),
                    contentDescription = "Иконка профиля",
                    modifier = Modifier
                        .size(sizeIcon),
                    tint = Color_MainText.value
                )
                Text(
                    text = "Профиль",
                    style = bottomTextStyle,
                    color = Color_MainText.value,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }
        }
    }
}