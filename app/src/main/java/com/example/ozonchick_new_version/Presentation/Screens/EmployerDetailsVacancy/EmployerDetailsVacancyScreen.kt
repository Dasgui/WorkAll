package com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.VacancyInformation
import com.example.ozonchick_new_version.Data.strings.AppErrors
import com.example.ozonchick_new_version.Presentation.States.DialogStates
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState.*
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomButton
import com.example.ozonchick_new_version.Presentation.ui.components.CustomComboBox
import com.example.ozonchick_new_version.Presentation.ui.components.CustomDialog
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomOutlinedTextField
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.NameTextField
import com.example.ozonchick_new_version.Presentation.ui.components.RowTextImage
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.R
import org.koin.androidx.compose.get
import org.koin.core.parameter.parametersOf

class EmployerDetailsVacancyScreen(private val information: VacancyInformation) : Screen {
    @Composable
    override fun Content() {
        val viewModel: EmployerDetailsVacancyViewModel = get { parametersOf(information ) }
        EmployerDetailsVacancyConstruct(information, viewModel)
    }

}

@Composable
private fun EmployerDetailsVacancyConstruct(
    information: VacancyInformation,
    viewModel: EmployerDetailsVacancyViewModel,
) {
    val localState = viewModel.screenState.collectAsState()
    val navigator = LocalNavigator.current

    LaunchedEffect(true) {
        viewModel.loadDataForEmployer()
    }

    if (viewModel.dialogState.value is DialogStates.Error) {
        FailAlertDialog(
            title = "Ошибка",
            AppErrors.INCORRECT_VALUES.description,
            onOkClick = {
                viewModel.dialogState.value = DialogStates.None
            })
    }

    if (viewModel.confirmCharge.value) {
        CustomDialog(
            onConfirmation = {
                Log.d("Повався", "Rjfds")
                viewModel.updateDescriptionInformation(
                    vacancyId = information.vakans_id,
                    navigator = navigator
                )
                viewModel.confirmCharge.value = false
//                navigator?.replaceAll(EmployerVacanciesScreen())
            },
            onDismissRequest = {
                viewModel.confirmCharge.value = false
            },
            dialogText = "Вы точно хотите изменить данные?",
        )
    }
    CustomScaffold(
        mainText = "Изменить данные",
        scaffoldStates = ScaffoldStates.OnSide
    ) {
        when (val curState = localState.value) {
            is Failure -> {
                FailAlertDialog(
                    title = "Ошибка",
                    curState.message,
                    onOkClick = {
                        viewModel.screenState.value = Error
                    })
            }

            is Loading -> CustomLoading()
            is Success -> {
                if (publicUserId.type is UserType.Admin) {
                    InformationForAdmin(viewModel)
                    VerticalSpacer(10.dp)
                }
                MainInformationVacancy(viewModel)

            }

            Error -> AlertText()
            AwaitingChangeFromUser -> Unit
            AwaitingResponse -> Unit
        }
    }
}

@Composable
private fun InformationForAdmin(viewModel : EmployerDetailsVacancyViewModel) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        RowTextImage(
            R.drawable.information_icon,
            title = "Информация о работодателе",
        )
        Information(title = "ИНН :", text = viewModel.inn.value)
        Information(title = "Название компании :", text = viewModel.companyName.value)
        Information(title = "Имя:", text = viewModel.name.value)
        Information(title = "Электронная почта :", text = viewModel.email.value)
    }
}

@Composable
private fun Information (title: String, text : String) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = title,
            style = Tema.Text_24_24_Medium,
            color = MainColor,
        )
        Text(
            text = text,
            style = Tema.Text_24_24_Medium,
            color = MainColor,
        )
    }
}

@Composable
private fun MainInformationVacancy (viewModel : EmployerDetailsVacancyViewModel) {
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
            itemToString = { it.name }
        )
//        NameTextField(
//            title = "Требуемый опыт работы*",
//            field = viewModel.experience,
//            holderText = ""
//        )
        VerticalSpacer(20.dp)
        CustomButton(
            onClick = {
                if (viewModel.checkEmail() && viewModel.checkPhoneNumber() && viewModel.aboutWork.value.isNotEmpty()
                    && viewModel.locationWork.value.isNotEmpty() && viewModel.experience.value.isNotEmpty() && viewModel.checkDigits()
                    && viewModel.specialization.value.isNotEmpty()) {
                    viewModel.confirmCharge.value = true
                } else {
                    viewModel.dialogState.value = DialogStates.Error
                }
            },
            placeText = "Изменить",
            reverseColors = true
        )
    }
}

@Composable
fun ColumItem(
    title : String = "",
    field : MutableState<String>,
    holderText : String = "",
    maxChar : Int,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NameTextField(
            title = title,
            field = field,
            holderText = holderText,
            maxChar = maxChar
        )
        Text(
            text = "${field.value.length}/$maxChar",
            color = MainColor,
            style = Tema.Text_24_24_Bold
        )
    }
}