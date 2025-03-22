package com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data.model.VacancyUpdateContact
import com.example.ozonchick_new_version.Data.model.VacancyUpdateInformation
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Presentation.Models.ChangeVacancyUIModel
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
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import org.koin.core.definition.indexKey
import org.koin.core.parameter.parametersOf

class EmployerDetailsVacancyScreen(private val information: VakansInformation) : Screen {
    @Composable
    override fun Content() {
        val viewModel: EmployerDetailsVacancyViewModel = get { parametersOf(information.vakans_id) }
        EmployerDetailsVacancyConstruct(information, viewModel)
    }

}

@Composable
private fun EmployerDetailsVacancyConstruct(
    information: VakansInformation,
    viewModel: EmployerDetailsVacancyViewModel,
) {
    viewModel.email.value = information.vakans_mail
    viewModel.price.value = information.vakans_mail

    viewModel.locationWork.value = information.vakans_location
    viewModel.specialization.value = information.vakans_name
    viewModel.experience.value = information.vakans_experience
    val localState = viewModel.screenState.collectAsState()
    val navigator = LocalNavigator.current

    if (viewModel.confirmCharge.value) {
        CustomDialog(
            onConfirmation = {
                viewModel.updateDescriptionInformation(
                    vacancyId = information.vakans_id,
                    information = ChangeVacancyUIModel(
                        mainInformation = VacancyUpdateInformation(
                            vakans_name = viewModel.specialization.value,
                            vakans_price = viewModel.price.value,
                            vakans_location = viewModel.locationWork.value,
                            vakans_experience = viewModel.experience.value,
                            vakans_mail = viewModel.email.value
                        ),
                        description = VacancyUpdateContact(
                            vakans_number = viewModel.phoneNumber.value,
                            vakans_mail = viewModel.email.value,
                            vakans_raspolog = viewModel.locationWork.value,
                            vakans_aboutWork = viewModel.aboutWork.value
                        )
                    ),
                    navigator = navigator
                )
                viewModel.confirmCharge.value = false
                navigator?.replaceAll(EmployerVacanciesScreen())
            },
            onDismissRequest = {
                viewModel.confirmCharge.value = false
            },
            dialogText = "Вы точно хотите изменить данные?",
        )
    }

    when (val curState = localState.value) {
        is ScreenState.Failure -> {
            FailAlertDialog(
                title = "Ошибка",
                curState.message,
                onOkClick = {
                    viewModel.screenState.value = ScreenState.Loading
                })
        }

        is ScreenState.Loading -> CustomLoading()
        is ScreenState.Success -> {
            CustomScaffold(
                mainText = "Изменить данные",
                scaffoldStates = ScaffoldStates.OnSide
            ) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 80.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    NameTextField(
                        title = "Требуемая специальность*",
                        field = viewModel.specialization,
                        holderText = ""
                    )
                    NameTextField(
                        title = "Заработная плата*",
                        field = viewModel.price,
                        holderText = ""
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
                        title = "Номер телефона*",
                        field = viewModel.phoneNumber,
                        holderText = ""
                    )
                    NameTextField(
                        title = "Почта*",
                        field = viewModel.email,
                        holderText = ""
                    )
                    NameTextField(
                        title = "Расположение места работы*",
                        field = viewModel.locationWork,
                        holderText = ""
                    )
                    NameTextField(
                        title = "Требуемый опыт работы*",
                        field = viewModel.experience,
                        holderText = ""
                    )
                    VerticalSpacer(20.dp)
                    CustomButton(
                        onClick = {
                            viewModel.confirmCharge.value = true
                        },
                        placeText = "Изменить",
                        reverseColors = true
                    )
                }
            }
        }
    }
}