package com.example.ozonchick_new_version.Presentation.ui.components.Test_Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ozonchick_new_version.R
import com.example.ozonchick_new_version.Presentation.Screens.ListVacanciesScreen
import cafe.adriel.voyager.navigator.Navigator
import androidx.compose.ui.text.TextStyle
import com.example.ozonchick_new_version.Presentation.Screens.Profile.ProfileScreen
import com.example.ozonchick_new_version.Presentation.Screens.ResponsesScreen
import com.example.ozonchick_new_version.Presentation.ui.components.HorizontalSpacer
import com.example.ozonchick_new_version.Presentation.ui.components.Test_Components.mini_components.CustomClickableIcon
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor

@Composable
fun ScaffoldBottomBar(
    navigator : Navigator?,
    sizeIcon : Dp = 35.dp,
    bottomTextStyle : TextStyle = Tema.Text_16_16_Medium
) {
    BottomAppBar(
        containerColor = BackgroundColor,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CustomClickableIcon(
                navigator = navigator,
                screen = ListVacanciesScreen(),
                iconId = R.drawable.vakansi_icon_3,
                sizeIcon = sizeIcon,
                colorIcon = MainColor,
                bottomText = "Вакансии",
                bottomTextStyle = bottomTextStyle,
                colorBottomText = MainColor
            )
            HorizontalSpacer(5.dp)
            CustomClickableIcon(
                navigator = navigator,
                screen = ResponsesScreen(),
                iconId = R.drawable.folder,
                sizeIcon = sizeIcon,
                colorIcon = MainColor,
                bottomText = "Отклики",
                bottomTextStyle = bottomTextStyle,
                colorBottomText = MainColor
            )
            HorizontalSpacer(5.dp)
            CustomClickableIcon(
                navigator = navigator,
                screen = ProfileScreen(),
                iconId = R.drawable.profile_circle,
                sizeIcon = sizeIcon,
                colorIcon = MainColor,
                bottomText = "Профиль",
                bottomTextStyle = bottomTextStyle,
                colorBottomText = MainColor
            )
        }
    }
}