package com.example.ozonchick_new_version.Presentation.Screens.Settings

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.ozonchick_new_version.Common.localAppTheme
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Common.model.AppTheme
import com.example.ozonchick_new_version.Common.model.UserMainInformation
import com.example.ozonchick_new_version.Presentation.States.ColorThemeStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.FirstBackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.FirstMainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.FirstSecondColor
import com.example.ozonchick_new_version.Presentation.ui.theme.FirstTransparentMainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondBackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondMainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondSecondColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondTransparentMainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.ThirdBackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.ThirdMainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.ThirdSecondColor
import com.example.ozonchick_new_version.Presentation.ui.theme.ThirdTransparentMainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.TransparentMainColor
import io.paperdb.Paper
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel : ViewModel() {
    val colorThemeState: MutableState<ColorThemeStates> = mutableStateOf(ColorThemeStates.BlueTheme)

    val messageSaveVisible : MutableState<Boolean> = mutableStateOf(false)

    fun switchColorTheme(colorTheme: ColorThemeStates) {
        when (colorTheme) {
            ColorThemeStates.BlueTheme -> {
                localAppTheme.mainColor = FirstMainColor
                localAppTheme.secondColor = FirstSecondColor
                localAppTheme.backgroundColor = FirstBackgroundColor
                localAppTheme.transparentColor = FirstTransparentMainColor

                updateMainColors()

                colorThemeState.value = ColorThemeStates.BlueTheme
            }

            ColorThemeStates.DarkTheme -> {
                localAppTheme.mainColor = SecondMainColor
                localAppTheme.secondColor = SecondSecondColor
                localAppTheme.backgroundColor = SecondBackgroundColor
                localAppTheme.transparentColor = SecondTransparentMainColor

                updateMainColors()

                colorThemeState.value = ColorThemeStates.DarkTheme
            }

            ColorThemeStates.GreenTheme -> {
                localAppTheme.mainColor = ThirdMainColor
                localAppTheme.secondColor = ThirdSecondColor
                localAppTheme.backgroundColor = ThirdBackgroundColor
                localAppTheme.transparentColor = ThirdTransparentMainColor

                updateMainColors()

                colorThemeState.value = ColorThemeStates.GreenTheme
            }
        }
    }

    fun updateMainColors () {
        MainColor = localAppTheme.mainColor
        SecondColor = localAppTheme.secondColor
        BackgroundColor = localAppTheme.backgroundColor
        TransparentMainColor = localAppTheme.transparentColor
    }

    fun saveColorTheme () {
        Paper.book().write<AppTheme>(
            "UserLocalAppTheme", AppTheme(
                localAppTheme.mainColor,
                localAppTheme.secondColor,
                localAppTheme.backgroundColor,
                localAppTheme.transparentColor,
            )
        )
        messageSaveVisible.value = true
    }
}