package com.example.ozonchick_new_version.Presentation.Screens.EmployerResponses

import android.util.Log
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
import com.example.ozonchick_new_version.Data.strings.AppErrors
import com.example.ozonchick_new_version.Presentation.Models.ExclusionOptions
import com.example.ozonchick_new_version.Presentation.Screens.EmployerCandidateInformation.EmployerCandidateInformationScreen
import com.example.ozonchick_new_version.Presentation.States.DialogStates
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
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
    val navigator = LocalNavigator.current
    LaunchedEffect(true) {
        viewModel.loadData()
    }
    val localState = viewModel.screenState.collectAsState()
    if (viewModel.dialogState.value is DialogStates.Error) {
        FailAlertDialog(
            title = "Ошибка",
            AppErrors.RESUME_NOT_EXIST.description,
            onOkClick = {
                viewModel.dialogState.value = DialogStates.None
            })
    }
    CustomScaffold(
        bottomButton = true,
        backButton = true,
        mainText = "Отклики",
        scaffoldStates = ScaffoldStates.OnSide
    ) {
        when (val curState = localState.value) {
            is Failure -> {
                FailAlertDialog(
                    title = "Ошибка",
                    curState.message,
                    onOkClick = {
                        viewModel.screenState.value = AwaitingChangeFromUser
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
                    for (item in curState.data.responseInfo ?: emptyList()) {
                        CandidateCard(
                            item,
                            acceptCandidate = {
                                viewModel.acceptCandidate(item.id)
                            },
                            rejectCandidate = {
                                viewModel.rejectCandidate(item.id)
                            },
                            navigateOnDescription = {
                                Log.d("ID кандидата", item.candidateInfo.id.toString())
                                navigator?.push(EmployerCandidateInformationScreen(item.candidateInfo.id))
//                                if (item.candidateInfo.id == 0) {
//                                    viewModel.dialogState.value = DialogStates.Error
//                                } else {
//
//                                }
                            },
                            candidateStatusId = item.status.id ?: 0
                        )
                    }
                }
            }

            is Error -> AlertText()
            AwaitingChangeFromUser -> AlertText(ExclusionOptions.NullCandidates.message)
            AwaitingResponse -> Unit
        }
    }

}