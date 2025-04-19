package com.example.ozonchick_new_version.Presentation.Screens.ListVacancies

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.ozonchick_new_version.Data.strings.AppErrors
import com.example.ozonchick_new_version.Presentation.States.DialogStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.Responses.VacancyCard
import org.koin.androidx.compose.koinViewModel

class ListVacanciesScreen() : Screen {
    @Composable
    override fun Content() {
        ListVacanciesScreenConstruct()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ListVacanciesScreenConstruct(viewModel: ListVacanciesViewModel = koinViewModel()) {
    if (viewModel.dialogStates.value is DialogStates.Error) {
        FailAlertDialog(
            title = "Ошибка",
            AppErrors.VACANCIES_ALREADY_EXIST.description,
            onOkClick = {
                viewModel.dialogStates.value = DialogStates.None
            })
    } else if (viewModel.dialogStates.value is DialogStates.Okay) {
        FailAlertDialog(
            title = "Отклик",
            "Вакансия добавлена в ваши отклики",
            onOkClick = {
                viewModel.dialogStates.value = DialogStates.None
            })
    }
    LaunchedEffect(true) {
        viewModel.loadData()
    }
    val screenState = viewModel.responseState.collectAsState()
    CustomScaffold(mainText = "Вакансии", bottomButton = true) {
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
                    viewModel.listVacancies.value.forEach { item ->
                        VacancyCard(item, addVacancy = {viewModel.addVacancy(item.vakans_id)})
                    }
                }

                is ScreenState.Error -> AlertText()
                ScreenState.Awaiting -> Unit
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