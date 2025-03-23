package com.example.ozonchick_new_version.Presentation.Screens.EmployerAddVacancy

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies.EmployerVacanciesScreen
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.components.CustomButton
import com.example.ozonchick_new_version.Presentation.ui.components.CustomDialog
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomOutlinedTextField
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.NameTextField
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import org.koin.androidx.compose.koinViewModel

class EmployerAddVacancyScreen : Screen {
    @Composable
    override fun Content() {
        EmployerAddVacancyConstruct()
    }
}

@Composable
fun EmployerAddVacancyConstruct(viewModel: EmployerAddVacancyViewModel = koinViewModel()) {
    val localState = viewModel.screenState.collectAsState()
    val navigator = LocalNavigator.current
    when (val curState = localState.value) {
        is ScreenState.Failure -> {
            FailAlertDialog(
                title = "Ошибка",
                curState.message,
                onOkClick = {
                    viewModel.screenState.value = ScreenState.Error
                })
        }

        is ScreenState.Loading -> CustomLoading()
        is ScreenState.Success -> navigator?.popUntil { it is EmployerVacanciesScreen }
        is ScreenState.Error -> Unit
    }
    if (viewModel.confirmCharge.value) {
        CustomDialog(
            onConfirmation = {
                viewModel.addVacancy(navigator)
                viewModel.confirmCharge.value = false
            },
            onDismissRequest = {
                viewModel.confirmCharge.value = false
            },
            dialogText = "Вы точно хотите создать вакансию?",
        )
    }
    CustomScaffold(
        mainText = "Создать вакансию",
        scaffoldStates = ScaffoldStates.OnSide
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 80.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            NameTextField(
                title = "Требуемая специальность*  ${viewModel.specialization.value.length}/50",
                field = viewModel.specialization,
                holderText = "",
                maxChar = 50
            )

            NameTextField(
                title = "Заработная плата*  ${viewModel.price.value.length}/20",
                field = viewModel.price,
                holderText = "",
                maxChar = 20
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "О работе*",
                        style = Tema.Text_24_24_Bold,
                        maxLines = 1,
                        color = MainColor,
                    )
                    Text(
                        text = "${viewModel.aboutWork.value.length}/500",
                        color = MainColor,
                        style = Tema.Text_24_24_Bold
                    )
                }
                CustomOutlinedTextField(
                    value = viewModel.aboutWork.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    onValueChange = {
                        if (viewModel.aboutWork.value.length < 500) {
                            viewModel.aboutWork.value = it
                        }
                    },
                    singleLine = false,
                )
            }
            NameTextField(
                title = "Номер телефона* ${viewModel.phoneNumber.value.length}/12",
                field = viewModel.phoneNumber,
                holderText = "",
                maxChar = 12
            )
            NameTextField(
                title = "Почта*",
                field = viewModel.email,
                holderText = ""
            )
            NameTextField(
                title = "Расположение места работы*  ${viewModel.locationWork.value.length}/50",
                field = viewModel.locationWork,
                holderText = "",
                maxChar = 50
            )
            NameTextField(
                title = "Требуемый опыт работы*",
                field = viewModel.experience,
                holderText = ""
            )
            VerticalSpacer(20.dp)
            CustomButton(
                onClick = {
                    if (viewModel.checkEmail() && viewModel.checkPhoneNumber() && viewModel.aboutWork.value.isNotEmpty()
                        && viewModel.locationWork.value.isNotEmpty() && viewModel.experience.value.isNotEmpty() && viewModel.price.value.isNotEmpty()
                        && viewModel.specialization.value.isNotEmpty()) {
                        viewModel.confirmCharge.value = true
                    } else {
                        viewModel.screenState.value = ScreenState.Failure(0, "Не все поля заполнены или заполнены некоректно. Проверить вписанные значения.")
                    }
                },
                placeText = "Создать вакансию",
                reverseColors = true
            )
        }
    }
}