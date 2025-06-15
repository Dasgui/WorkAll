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
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Presentation.Screens.ChangeInformation.ChangeInformationScreen
import com.example.ozonchick_new_version.Presentation.Screens.LogIn.LogInScreen
import com.example.ozonchick_new_version.Presentation.Screens.Resume.ResumeScreen
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomCard
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.HorizontalSpacer
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
        viewModel.loadData()
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
                        ScreenHead(navigator = navigator, curState.data)
                        ScreenBody(navigator = navigator)
                    }

                    ScreenState.Error -> AlertText()
                    ScreenState.AwaitingChangeFromUser -> Unit
                    ScreenState.AwaitingResponse -> Unit
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
    Row(
        modifier = Modifier.Companion.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.good_tick),
            contentDescription = "Значок подтверждения полной регистрации",
            contentScale = ContentScale.Companion.Crop,
            modifier = Modifier.Companion
                .size(50.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            style = Tema.Text_24_24_Bold,
            color = MainColor,
            modifier = Modifier.weight(1f).padding(start = 10.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_round_log_out),
            contentDescription = "Выход",
            modifier = Modifier.Companion
                .size(50.dp)
                .clickable {
                    Paper.book().delete("UserLocalInformation")
                    navigator?.replaceAll(LogInScreen())
                },
            colorFilter = ColorFilter.Companion.tint(MainColor)
        )
    }
}

@Composable
private fun ScreenBody(navigator: Navigator?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
    ) {
//        CardConstruct(
//            "Настройки",
//            "Здесь вы можете настроить приложение",
//            R.drawable.setting_icon,
//            navigateOnScreen = {}
//        )
        CardConstruct(
            "Информация о вас",
            "Здесь вы можете изменить информацию о себе",
            R.drawable.edit_icon,
            navigateOnScreen = {
                navigator?.push(ChangeInformationScreen())
            }
        )
        if (localUserInformation.type is UserType.Candidate) {
            CardConstruct(
                "Ваше резюме",
                "Здесь вы можете написать своё резюме",
                R.drawable.resume_icon,
                navigateOnScreen = {
                    navigator?.push(ResumeScreen())
                }
            )
        }
    }
}

@Composable
private fun CardConstruct(
    mainText: String,
    secondText: String,
    icon: Int,
    navigateOnScreen: () -> Unit
) {
    CustomCard(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(vertical = 13.dp)
            .height(80.dp)
            .clickable { navigateOnScreen() },
    ) {
        Row(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
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
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = icon),
            contentDescription = "Значок",
            tint = MainColor
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(
                text = mainText,
                style = Tema.Text_24_24_Bold,
                color = MainColor,
            )
            Text(
                text = secondText,
                style = Tema.Text_18_18_Medium,
                color = MainColor,
            )
        }
    }
}