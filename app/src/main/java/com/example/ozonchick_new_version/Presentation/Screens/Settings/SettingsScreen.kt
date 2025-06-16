package com.example.ozonchick_new_version.Presentation.Screens.Settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.ozonchick_new_version.Presentation.States.ColorThemeStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomButton
import com.example.ozonchick_new_version.Presentation.ui.components.CustomCard
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.theme.FirstBackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.FirstMainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondBackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondMainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.ThirdBackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.ThirdMainColor
import org.koin.androidx.compose.koinViewModel

class SettingsScreen : Screen {
    @Composable
    override fun Content() {
        SettingsConstruct()
    }

    @Composable
    fun SettingsConstruct (viewModel : SettingsViewModel = koinViewModel()) {
        CustomScaffold(
            mainText = "Настройки",
            bottomButton = true,
            backButton = true,
            mainBody = {
                if (viewModel.messageSaveVisible.value) {
                    FailAlertDialog(
                        title = "Сохранено",
                        "Цветовая схема сохранена",
                        onOkClick = {
                            viewModel.messageSaveVisible.value = false
                        })
                }
                Column(
                    modifier = Modifier.Companion
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    CustomButton(
                        onClick = {
                            viewModel.switchColorTheme(ColorThemeStates.BlueTheme)
                        },
                        backgroundColor = FirstBackgroundColor,
                        mainColor = FirstMainColor,
                        placeText = "Первый вариант"
                    )

                    CustomButton(
                        onClick = {
                            viewModel.switchColorTheme(ColorThemeStates.DarkTheme)
                        },
                        backgroundColor = SecondBackgroundColor,
                        mainColor = SecondMainColor,
                        placeText = "Второй вариант"
                    )

                    CustomButton(
                        onClick = {
                            viewModel.switchColorTheme(ColorThemeStates.GreenTheme)
                        },
                        backgroundColor = ThirdBackgroundColor,
                        mainColor = ThirdMainColor,
                        placeText = "Третий вариант"
                    )

                    VerticalSpacer(5.dp)

                    CustomButton(
                        onClick = {
                            viewModel.saveColorTheme()
                        },
                        placeText = "Сохранить цвет"
                    )
                }
            }
        )
    }
}