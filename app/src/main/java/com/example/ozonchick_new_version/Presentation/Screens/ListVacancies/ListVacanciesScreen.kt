package com.example.ozonchick_new_version.Presentation.Screens.ListVacancies

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.localUserInformation
import com.example.ozonchick_new_version.Data.model.ListOfVacancies.StatesOfGetVacancies
import com.example.ozonchick_new_version.Data.strings.AppErrors
import com.example.ozonchick_new_version.Presentation.Screens.ContactsScreen
import com.example.ozonchick_new_version.Presentation.Screens.Search.SearchScreen
import com.example.ozonchick_new_version.Presentation.States.DialogStates
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomButton
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.Responses.VacancyCard
import org.koin.androidx.compose.koinViewModel

class ListVacanciesScreen(private val statesOfGetVacancies: StatesOfGetVacancies = StatesOfGetVacancies.Normal) :
    Screen {
    @Composable
    override fun Content() {
        ListVacanciesScreenConstruct(statesOfGetVacancies = statesOfGetVacancies)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ListVacanciesScreenConstruct(
    viewModel: ListVacanciesViewModel = koinViewModel(),
    statesOfGetVacancies: StatesOfGetVacancies
) {
    val navigator = LocalNavigator.current
    LaunchedEffect(true) {
        viewModel.responseState.value = ScreenState.Loading
        viewModel.statesOfGetVacancies.value = statesOfGetVacancies
        viewModel.loadData()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (viewModel.dialogStates.value) {
            DialogStates.Error -> {
                FailAlertDialog(
                    title = "Ошибка",
                    AppErrors.VACANCIES_ALREADY_EXIST.description,
                    onOkClick = {
                        viewModel.dialogStates.value = DialogStates.None
                    })
            }
            DialogStates.None -> Unit
            DialogStates.Okay -> {
                FailAlertDialog(
                    title = "Отклик",
                    "Вакансия добавлена в ваши отклики",
                    onOkClick = {
                        viewModel.dialogStates.value = DialogStates.None
                    })
            }
        }
    }
//    if (viewModel.dialogStates.value is DialogStates.Error) {
//        FailAlertDialog(
//            title = "Ошибка",
//            AppErrors.VACANCIES_ALREADY_EXIST.description,
//            onOkClick = {
//                viewModel.dialogStates.value = DialogStates.None
//            })
//    } else if (viewModel.dialogStates.value is DialogStates.Okay) {
//        FailAlertDialog(
//            title = "Отклик",
//            "Вакансия добавлена в ваши отклики",
//            onOkClick = {
//                viewModel.dialogStates.value = DialogStates.None
//            })
//    }

    val screenState = viewModel.responseState.collectAsState()
    CustomScaffold(
        mainText = "Вакансии",
        bottomButton = true,
        scaffoldStates = ScaffoldStates.Vacancies,
        statesOfGetVacancies = viewModel.statesOfGetVacancies.value
    ) {
        Column(
            modifier = Modifier.Companion
                .padding(bottom = 80.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            when (val curState = screenState.value) {
                is ScreenState.Failure -> FailAlertDialog(
                    title = "Ошибка",
                    curState.message,
                    onOkClick = {
                        viewModel.responseState.value = ScreenState.Error
                    })

                is ScreenState.Loading -> CustomLoading()
                is ScreenState.Success -> {
                    for (item in viewModel.listVacancies.value.vacanciesInfo ?: emptyList()) {
                        VacancyCard(
                            item,
                            buttons = {
                                when (localUserInformation.type) {
                                    is UserType.Admin -> {
                                        CustomButton(
                                            onClick = {
//                                                navigator?.push(EmployerDetailsVacancyScreen(item))
                                            },
                                            placeText = "Подробнее",
                                            modifier = Modifier.fillMaxWidth(),
                                            reverseColors = true
                                        )
                                    }
                                    is UserType.Candidate -> {
                                        CustomButton(
                                            onClick = { viewModel.addVacancy(item.id) },
                                            modifier = Modifier.fillMaxWidth(),
                                            placeText = "Откликнуться"
                                        )
                                        CustomButton(
                                            onClick = {
                                                navigator?.push(ContactsScreen(item.id))
                                            },
                                            placeText = "Подробнее",
                                            modifier = Modifier.fillMaxWidth(),
                                            reverseColors = true
                                        )
                                    }
                                    is UserType.Employer -> {
                                        CustomButton(
                                            onClick = {
                                                navigator?.push(ContactsScreen(item.id))
                                            },
                                            placeText = "Подробнее",
                                            modifier = Modifier.fillMaxWidth(),
                                            reverseColors = true
                                        )
                                    }
                                    is UserType.None -> Unit
                                }
                            })
                    }
                }

                is ScreenState.Error -> AlertText()
                ScreenState.AwaitingChangeFromUser -> AlertText(AppErrors.NO_FIND_VACANCIES.description, true, backOn = {navigator?.push(SearchScreen(statesOfGetVacancies))})
                ScreenState.AwaitingResponse -> Unit
            }
        }
    }

}

//@Composable
//private fun MainScreen(viewModel: ListVacanciesViewModel) {
//    CustomScaffold(mainText = "Вакансии", bottomButton = true) {
//        Column(
//            modifier = Modifier.Companion
//                .padding(bottom = 80.dp)
//                .fillMaxWidth(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.Companion.CenterHorizontally
//        ) {
//            viewModel.listVacancies.value.forEach { item ->
//                VacancyCard(item)
//            }
//        }
//    }
//}