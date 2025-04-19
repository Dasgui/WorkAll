package com.example.ozonchick_new_version.Presentation.Screens.ChangeInformation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.ozonchick_new_version.Data.strings.AppErrors
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.States.TextFieldStates
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomButton
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomOutlinedTextField
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.HorizontalSpacer
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.R
import org.koin.androidx.compose.koinViewModel

class ChangeInformationScreen : Screen {
    @Composable
    override fun Content() {
        ChangeInformationConstruct()
    }
}

@Composable
private fun ChangeInformationConstruct (viewModel: ChangeInformationViewModel = koinViewModel()) {
    CustomScaffold (
        bottomButton = true,
        scaffoldStates = ScaffoldStates.OnSide,
        mainText = "Информация о вас",
        mainBody = {
            val localState = viewModel.screenState.collectAsState()
            when (val curState = localState.value) {
                ScreenState.Error -> {
                    FailAlertDialog(
                        title = "Ошибка",
                        AppErrors.INCORRECT_VALUES.description,
                        onOkClick = {
                            viewModel.screenState.value = ScreenState.Awaiting
                        })
                }
                is ScreenState.Failure -> {
                    AlertText()
//                    FailAlertDialog(
//                        title = "Ошибка",
//                        curState.message,
//                        onOkClick = {
//                            viewModel.screenState.value = ScreenState.Awaiting
//                        })
                }
                ScreenState.Loading -> CustomLoading()
                is ScreenState.Success<*> -> {
                    FailAlertDialog(
                        title = "Отлично",
                        "Информация обновлена",
                        onOkClick = {
                            viewModel.screenState.value = ScreenState.Awaiting
                        })
                }

                ScreenState.Awaiting -> {
                    Column (
                        modifier = Modifier.fillMaxSize()
                    ) {
                        TextFieldRowInformation(
                            text = "Почта",
                            image = R.drawable.email,
                            changeValue = viewModel.email,
                        )
                        VerticalSpacer(30.dp)

                        TextFieldRowInformation(
                            text = "Пароль",
                            image = R.drawable.password_icon,
                            changeValue = viewModel.password,
                            isPassword = true
                        )
                        VerticalSpacer(30.dp)

                        TextFieldRowInformation(
                            text = "Имя",
                            image = R.drawable.profile,
                            changeValue = viewModel.name,
                        )
                        VerticalSpacer(30.dp)

                        TextFieldRowInformation(
                            text = "Номер телефона",
                            image = R.drawable.call,
                            changeValue = viewModel.phoneNumber,
                        )

                        VerticalSpacer(50.dp)

                        CustomButton(
                            onClick = {
                                viewModel.updateInformation()
                            },
                            placeText = "Сохранить изменения"
                        )
                        VerticalSpacer(30.dp)
                    }
                }
            }
        }
    )
}

@Composable
private fun TextFieldRowInformation (
    text : String,
    image : Int,
    changeValue : MutableState<String>,
    isPassword : Boolean = false
) {
    RowInformation(
        text = text,
        image = image
    )
    VerticalSpacer(5.dp)
    CustomOutlinedTextField(
        value = changeValue.value,
        onValueChange = {changeValue.value = it},
        states = if (isPassword) TextFieldStates.Password else TextFieldStates.Normal
    )
}

@Composable
private fun RowInformation (
    text : String,
    image : Int
) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = text,
            style = Tema.Text_24_24_Bold,
            color = MainColor,
        )
        HorizontalSpacer(10.dp)
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(MainColor)
        )

    }
}