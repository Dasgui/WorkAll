package com.example.ozonchick_new_version.Presentation.Screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.ozonchick_new_version.Data.data.db.idSharedUser
import com.example.ozonchick_new_version.Data.model.Candidate.UserInformation
import com.example.ozonchick_new_version.Presentation.Models.ResponsesUIModel
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ViewModels.ResponsesViewModel
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.ResponseVacancyCard
import com.example.ozonchick_new_version.Presentation.ui.components.Responses.VacancyCard
import com.example.ozonchick_new_version.Presentation.ui.theme.Ozonchick_new_versionTheme
import org.koin.androidx.compose.koinViewModel

class ResponsesScreen : Screen {
    @Composable
    override fun Content() {
        ResponseConstruct()
    }
}

@Composable
private fun ResponseConstruct(viewModel: ResponsesViewModel = koinViewModel())
{
    val localState = viewModel.responseState.collectAsState()
    when (val curState = localState.value) {
        is ScreenState.Failure -> FailAlertDialog(
            title = "Ошибка",
            curState.message,
            onOkClick = {
                viewModel.responseState.value = ScreenState.Loading
            })
        ScreenState.Loading -> CustomLoading()
        is ScreenState.Success -> MainBody(curState.data)
    }
}


@Composable
private fun MainBody(vacancies: ResponsesUIModel)
{
    CustomScaffold(
        bottomButton = true,
        mainText = "Отклики"
    ) {
        Column (
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(bottom = 80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            Log.d("Размерность списка состояния откликов", vacancies.status.size.toString())
            Log.d("Размерность списка полученных вакансий", vacancies.information.size.toString())
            for (i in 0 until vacancies.status.size) {
                ResponseVacancyCard(
                    list = vacancies.information[i],
                    status = vacancies.status[i].otvet
                )
            }
        }
    }

}