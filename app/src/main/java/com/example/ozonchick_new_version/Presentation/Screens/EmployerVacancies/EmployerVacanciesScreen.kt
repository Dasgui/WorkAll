package com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Presentation.Screens.ContactsScreen
import com.example.ozonchick_new_version.Presentation.Screens.EmployerAddVacancy.EmployerAddVacancyScreen
import com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy.EmployerDetailsVacancyScreen
import com.example.ozonchick_new_version.Presentation.Screens.EmployerResponses.EmployerResponsesScreen
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomButton
import com.example.ozonchick_new_version.Presentation.ui.components.CustomDialog
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.EmployerVacancyCard
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.Responses.VacancyCard
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.EnabledColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.Presentation.ui.theme.SwitchOffColor
import org.koin.androidx.compose.koinViewModel

class EmployerVacanciesScreen : Screen {
    @Composable
    override fun Content() {
        EmployerVacanciesConstruct()
    }
}

@Composable
private fun EmployerVacanciesConstruct(viewModel: EmployerVacanciesViewModel = koinViewModel()) {
    LaunchedEffect(true) {
        viewModel.loadData()
    }
    val localState = viewModel.screenState.collectAsState()
    val navigator = LocalNavigator.current
    CustomScaffold(
        bottomButton = true,
        mainText = "Мои вакансии",
        scaffoldStates = ScaffoldStates.EmployerVacancies,
        addVacancy = {
            navigator?.push(EmployerAddVacancyScreen())
        }
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
            is ScreenState.Success -> {
                if (viewModel.confirmDelete.value) {
                    CustomDialog(
                        onConfirmation = {
                            viewModel.deleteVacancy(viewModel.vacancyId.value)
                            viewModel.confirmDelete.value = false
                        },
                        onDismissRequest = { viewModel.confirmDelete.value = false },
                        dialogText = "Вы точно хотите удалить вакансию?"
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(bottom = 80.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    //ИЗМЕНИТЬ
                    for (vacancy in curState.data.vacanciesInfo) {
                        VacancyCard(
                            vacancy,
                            buttons = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 10.dp),
                                    verticalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    if (vacancy.isVisible) {
                                        CustomButton(
                                            onClick = {
                                                navigator?.push(
                                                    EmployerDetailsVacancyScreen(vacancy.id)
                                                )
                                            },
                                            placeText = "Изменить",
                                            reverseColors = true,
                                            modifier = Modifier.fillMaxWidth(),
                                        )
                                        CustomButton(
                                            onClick = {
                                                navigator?.push(EmployerResponsesScreen(vacancy.id))
                                            },
                                            placeText = "Соискатели",
                                            reverseColors = true,
                                            modifier = Modifier.fillMaxWidth(),

                                            )
                                        CustomButton(
                                            onClick = {
                                                viewModel.confirmDelete.value = true
                                                viewModel.vacancyId.value = vacancy.id
                                                Log.d("Id вакансии", viewModel.vacancyId.value.toString())
                                            },
                                            placeText = "Удалить",
                                            modifier = Modifier.fillMaxWidth(),
                                        )
                                    } else {
                                        Text(
                                            text = "Вакансия скрыта",
                                            style = Tema.Text_20_20_Medium,
                                            textAlign = TextAlign.Center,
                                            color = MainColor
                                        )
                                        if (viewModel.visibleButtons.value) {
                                            CustomButton(
                                                onClick = {
                                                    navigator?.push(
                                                        EmployerDetailsVacancyScreen(vacancy.id)
                                                    )
                                                },
                                                placeText = "Изменить",
                                                reverseColors = true,
                                                modifier = Modifier.fillMaxWidth(),
                                            )
                                            CustomButton(
                                                onClick = {
                                                    navigator?.push(EmployerResponsesScreen(vacancy.id))
                                                },
                                                placeText = "Соискатели",
                                                reverseColors = true,
                                                modifier = Modifier.fillMaxWidth(),

                                                )
                                            CustomButton(
                                                onClick = {
                                                    viewModel.confirmDelete.value = true
                                                    viewModel.vacancyId.value = vacancy.id
                                                    Log.d("Id вакансии", viewModel.vacancyId.value.toString())
                                                },
                                                placeText = "Удалить",
                                                modifier = Modifier.fillMaxWidth(),
                                            )
                                        }
                                    }
                                }

                            },
                            click = {
                                viewModel.visibleButtons.value = !viewModel.visibleButtons.value
                            },
                            mainTextVisible = viewModel.visibleButtons.value && vacancy.isVisible
                        )
                    }
                }

            }

            ScreenState.Error -> AlertText()
            ScreenState.AwaitingChangeFromUser -> Unit
            ScreenState.AwaitingResponse -> Unit
        }
    }

}