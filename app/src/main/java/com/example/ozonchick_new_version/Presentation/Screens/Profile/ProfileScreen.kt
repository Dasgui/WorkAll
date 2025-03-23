package com.example.ozonchick_new_version.Presentation.Screens.Profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Common.isLocalVersion
import com.example.ozonchick_new_version.Presentation.Screens.LogIn.LogInScreen
import com.example.ozonchick_new_version.Presentation.Screens.Settings_Screen
import com.example.ozonchick_new_version.Presentation.Screens.VersionConstruct
import com.example.ozonchick_new_version.Presentation.Screens.VersionScreen
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomCard
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.R
import io.paperdb.Paper
import org.koin.androidx.compose.koinViewModel

class ProfileScreen() : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
//        val viewModel: ProfileViewModel = getViewModel { parametersOf(userId) }
        ProfileConstruct(navigator/*, viewModel*/)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ProfileConstruct(navigator: Navigator?, viewModel: ProfileViewModel = koinViewModel()) {
    LaunchedEffect(true) {
        if (!isLocalVersion) {
            viewModel.loadData()
        } else {
            viewModel.loadLocalData()
        }

    }
    val screenState = viewModel.informationProfileState.collectAsState()
    CustomScaffold(
        mainText = "Профиль",
        bottomButton = true,
        mainBody = {
            Column(
                modifier = Modifier.Companion
                    .fillMaxSize()
            ) {
                when (val curState = screenState.value) {
                    is ScreenState.Failure -> FailAlertDialog(
                        title = "Ошибка",
                        curState.message,
                        onOkClick = {
                            viewModel.informationProfileState.value = ScreenState.Error
                        })
                    is ScreenState.Loading -> CustomLoading()
                    is ScreenState.Success -> {
                        ScreenHead(navigator = navigator, curState.data.name)
                        ScreenBody(navigator = navigator)
                    }
                    ScreenState.Error -> AlertText()
                }
            }
        }
    )
}

@Composable
private fun ScreenHead(
    navigator: Navigator?,
    name: String
) {
    Row(modifier = Modifier.Companion.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.good_tick),
            contentDescription = "Значок подтверждения полной регистрации",
            contentScale = ContentScale.Companion.Crop,
            modifier = Modifier.Companion
                .size(50.dp)
                .clip(CircleShape)
        )
        Row(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(top = 5.dp), horizontalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.Companion.Start,
                modifier = Modifier.Companion.padding(start = 10.dp)
            ) {
                Text(
                    text = name,
                    style = Tema.Text_24_24_Bold,
                    color = MainColor
                )
            }
            Column(
                horizontalAlignment = Alignment.Companion.End,
                modifier = Modifier.Companion.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_round_log_out),
                    contentDescription = "Выход",
                    modifier = Modifier.Companion
                        .size(50.dp)
                        .clickable {
                            Paper.book().delete("Version")
                            Paper.book().delete("UserId")
                            if (isLocalVersion == true) {
                                navigator?.replaceAll(VersionScreen())
                            } else {
                                navigator?.replaceAll(LogInScreen())
                            }
                        },
                    colorFilter = ColorFilter.Companion.tint(MainColor)
                )
            }
        }
    }
}

@Composable
private fun ScreenBody(navigator: Navigator?) {
    Column(
        modifier = Modifier.Companion
            .fillMaxSize()
            .padding(top = 10.dp),
    ) {
        Card_construct(
            "Настройки",
            "Здесь вы можете настроить приложение",
            R.drawable.setting_icon,
            navigator
        )
        Card_construct(
            "Информация о вас",
            "Здесь вы можете изменить информация",
            R.drawable.setting_icon,
            navigator
        )
    }
}

@Composable
private fun Card_construct(
    mainText: String,
    secondText: String,
    icon: Int,
    localNavigator: Navigator?
) {
    CustomCard(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(top = 20.dp)
            .height(80.dp)
            .clickable { /*localNavigator?.push(Settings_Screen())*/ },
    ) {
        Row(
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            CardMain(mainText = mainText, secondText = secondText, icon = icon)
        }
    }
}

@Composable
private fun CardMain(
    mainText: String,
    secondText: String,
    icon: Int
) {
    Row(
        verticalAlignment = Alignment.Companion.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            modifier = Modifier.Companion.size(25.dp),
            painter = painterResource(id = icon),
            contentDescription = "Значок",
            tint = MainColor
        )
        Column(
            horizontalAlignment = Alignment.Companion.Start,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.Companion.padding(start = 5.dp)
        ) {
            Text(
                text = mainText,
                style = Tema.Text_24_24_Bold,
                color = MainColor,
            )
            Text(
                text = secondText,
                style = Tema.Text_20_20_Medium,
                color = MainColor,
            )
        }
    }
    Icon(
        modifier = Modifier.Companion.size(20.dp),
        painter = painterResource(id = R.drawable.vector),
        contentDescription = "",
        tint = MainColor
    )
}