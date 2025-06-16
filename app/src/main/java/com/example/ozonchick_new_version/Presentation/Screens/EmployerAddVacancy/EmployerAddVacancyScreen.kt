package com.example.ozonchick_new_version.Presentation.Screens.EmployerAddVacancy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data.strings.AppErrors
import com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies.EmployerVacanciesScreen
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomButton
import com.example.ozonchick_new_version.Presentation.ui.components.CustomComboBox
import com.example.ozonchick_new_version.Presentation.ui.components.CustomDialog
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomOutlinedTextField
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.NameTextField
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondColor
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
    LaunchedEffect(true) {
        viewModel.loadData()
    }
    CustomScaffold(
        mainText = "Создать вакансию",
        scaffoldStates = ScaffoldStates.OnSide,
        backButton = true
    ) {
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
            is ScreenState.Error -> AlertText()
            ScreenState.AwaitingChangeFromUser -> SuccessBody(viewModel)
            ScreenState.AwaitingResponse -> Unit
        }
        if (viewModel.confirmCharge.value) {
            FailAlertDialog(
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
        if (viewModel.unCorrectInformation.value) {
            FailAlertDialog(
                title = "Ошибка",
                AppErrors.INCORRECT_VALUES.description,
                onOkClick = {
                    viewModel.unCorrectInformation.value = false
                })
        }
    }


}

@Composable
private fun SuccessBody(viewModel: EmployerAddVacancyViewModel) {
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
            maxChar = 50,
            indicatorColor = if (viewModel.checkSpecialization(viewModel.specialization.value)) MainColor else Color.Red
        )

        NameTextField(
            title = "Заработная плата*  ${viewModel.price.value.length}/8",
            field = viewModel.price,
            holderText = "",
            maxChar = 8,
            indicatorColor = if (viewModel.checkPrice(viewModel.price.value)) MainColor else Color.Red
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
                    text = "${viewModel.aboutWork.value.length}/2000",
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
                    if (viewModel.aboutWork.value.length < 2000) {
                        viewModel.aboutWork.value = it
                    }
                },
                singleLine = false,
                indicatorColor = if (viewModel.checkAboutWork(viewModel.aboutWork.value)) MainColor else Color.Red
            )
        }
        NameTextField(
            title = "Номер телефона* ${viewModel.phoneNumber.value.length}/12",
            field = viewModel.phoneNumber,
            holderText = "",
            maxChar = 12,
            indicatorColor = if (viewModel.checkPhoneNumber(viewModel.phoneNumber.value)) MainColor else Color.Red
        )
        NameTextField(
            title = "Почта*",
            field = viewModel.email,
            holderText = "",
            indicatorColor = if (viewModel.checkEmail(viewModel.email.value)) MainColor else Color.Red
        )
        NameTextField(
            title = "Расположение места работы*  ${viewModel.locationWork.value.length}/50",
            field = viewModel.locationWork,
            holderText = "",
            maxChar = 50,
            indicatorColor = if (viewModel.checkLocationWork(viewModel.locationWork.value)) MainColor else Color.Red
        )
        Text(
            text = "Требуемый опыт работы*",
            style = Tema.Text_24_24_Bold,
            maxLines = 1,
            color = MainColor,
        )
        VerticalSpacer(10.dp)
        CustomComboBox(
            textField = viewModel.selectedExperience.value?.name ?: "Выберите значение",
            dropItemsList = viewModel.experienceList.value,
            selectedChange = { experience ->
                viewModel.selectedExperience.value = experience
            },
            enabled = true,
            itemToString = { it.name },
            indicatorColor = if (viewModel.checkExperience(viewModel.selectedExperience.value?.name) ?: false) MainColor else Color.Red
        )
        VerticalSpacer(20.dp)
        CustomButton(
            onClick = {
                if (viewModel.checkFields()) {
                    viewModel.confirmCharge.value = true
                } else {
                    viewModel.screenState.value = ScreenState.AwaitingChangeFromUser
                }
            },
            placeText = "Создать вакансию",
            reverseColors = true
        )
    }
}