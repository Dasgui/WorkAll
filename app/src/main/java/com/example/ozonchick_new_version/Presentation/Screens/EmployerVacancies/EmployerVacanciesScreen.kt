package com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Presentation.Screens.EmployerAddVacancy.EmployerAddVacancyScreen
import com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy.EmployerDetailsVacancyScreen
import com.example.ozonchick_new_version.Presentation.Screens.EmployerResponses.EmployerResponsesScreen
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomDialog
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.EmployerVacancyCard
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
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
                        onDismissRequest = {viewModel.confirmDelete.value = false},
                        dialogText = "Вы точно хотите удалить вакансию?"
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(bottom = 80.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    curState.data.forEach { item ->
                        EmployerVacancyCard(
                            item,
                            deleteVacancy = {
                                viewModel.confirmDelete.value = true
                                viewModel.vacancyId.value = item.vakans_id
                            },
                            changeVacancy = {
                                navigator?.push(
                                    EmployerDetailsVacancyScreen(item)
                                )
                            },
                            onNavigateForResponses = {
                                navigator?.push(EmployerResponsesScreen(item.vakans_id))
                            }
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