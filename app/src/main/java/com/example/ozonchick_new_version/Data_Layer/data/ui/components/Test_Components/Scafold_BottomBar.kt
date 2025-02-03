package com.example.ozonchick_new_version.Data_Layer.data.ui.components.Test_Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.backgroundColor
import com.example.ozonchick_new_version.Data_Layer.data.ui.theme.mainColor
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Screens.Home.Profile_Screen
import com.example.ozonchick_new_version.Screens.ListVacancies.ListVacanciesScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data_Layer.data.ui.text_tema.Tema
import androidx.compose.ui.text.TextStyle
import com.example.ozonchick_new_version.Data_Layer.data.ui.components.Test_Components.mini_components.Clickable_Icon
import com.example.ozonchick_new_version.Screens.Responses.ResponsesScreen

@Composable
fun Scafold_BottomBar(
    navigator : Navigator?,
    sizeIcon : Dp = 35.dp,
    bottomTextStyle : TextStyle = Tema.Text_16_16_Medium
) {
    BottomAppBar(
        containerColor = backgroundColor,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Clickable_Icon(
                navigator = navigator,
                screen = ListVacanciesScreen(),
                iconId = R.drawable.vakansi_icon_3,
                sizeIcon = sizeIcon,
                colorIcon = mainColor,
                bottomText = "Вакансии",
                bottomTextStyle = bottomTextStyle,
                colorBottomText = mainColor
            )
            Clickable_Icon(
                navigator = navigator,
                screen = ResponsesScreen(),
                iconId = R.drawable.folder,
                sizeIcon = sizeIcon,
                colorIcon = mainColor,
                bottomText = "Собственные вакансии",
                bottomTextStyle = bottomTextStyle,
                colorBottomText = mainColor
            )
            Clickable_Icon(
                navigator = navigator,
                screen = ResponsesScreen()/*Profile_Screen()*/,
                iconId = R.drawable.profile_circle,
                sizeIcon = sizeIcon,
                colorIcon = mainColor,
                bottomText = "Профиль",
                bottomTextStyle = bottomTextStyle,
                colorBottomText = mainColor
            )
        }
    }
}