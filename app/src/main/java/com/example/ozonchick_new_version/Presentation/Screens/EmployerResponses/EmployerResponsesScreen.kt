package com.example.ozonchick_new_version.Presentation.Screens.EmployerResponses

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
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState.*
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CandidateCard
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import org.koin.androidx.compose.get
import org.koin.core.parameter.parametersOf

class EmployerResponsesScreen(private val vacancyId: Int) : Screen {
    @Composable
    override fun Content() {
        val viewModel: EmployerResponsesViewModel = get { parametersOf(vacancyId) }
        EmployerResponsesConstruct(viewModel)
    }
}

@Composable
private fun EmployerResponsesConstruct(viewModel: EmployerResponsesViewModel) {
    LaunchedEffect(true) {
        viewModel.loadData()
    }
    val localState = viewModel.screenState.collectAsState()
    CustomScaffold(
        bottomButton = true,
        mainText = "Отклики",
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
                Column (
                    modifier = Modifier
                        .padding(bottom = 80.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    curState.data.forEach { item ->
                        CandidateCard(
                            item,
                            acceptCandidate = {
                                viewModel.acceptCandidate(item.user_id)
                            },
                            rejectCandidate = {
                                viewModel.rejectCandidate(item.user_id)
                            },
                            navigateOnDescription = {
                                viewModel.screenState.value = Failure(0, "Простите, но дополнительная информация пока не доступна.")
                            }
                        )
                    }
                }
            }

            is Error -> AlertText()
            AwaitingChangeFromUser -> Unit
            AwaitingResponse -> Unit
        }
    }

}