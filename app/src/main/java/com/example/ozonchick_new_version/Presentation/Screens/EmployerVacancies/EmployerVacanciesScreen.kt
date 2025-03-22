package com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.EmployerVacansCard
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
    val localState = viewModel.screenState.collectAsState()
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
                bottomButton = true,
                mainText = "Мои вакансии",
                scaffoldStates = ScaffoldStates.EmployerVacancies
            ) {
                Column (
                    modifier = Modifier.padding(bottom = 80.dp).fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    curState.data.forEach { item ->
                        EmployerVacansCard(item)
                    }
                }
            }
        }
    }
}