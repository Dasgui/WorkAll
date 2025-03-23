package com.example.ozonchick_new_version.Presentation.Screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.ozonchick_new_version.Presentation.Models.ResponsesUIModel
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ViewModels.ResponsesViewModel
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.ResponseVacancyCard
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
    LaunchedEffect(true) {
        viewModel.responseState.value = ScreenState.Loading
        viewModel.loadData()
    }
    val localState = viewModel.responseState.collectAsState()
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
            when (val curState = localState.value) {
                is ScreenState.Failure -> FailAlertDialog(
                    title = "Ошибка",
                    curState.message,
                    onOkClick = {
                        viewModel.responseState.value = ScreenState.Error
                    })
                ScreenState.Loading -> CustomLoading()
                is ScreenState.Success -> {
                    Log.d("Размерность списка состояния откликов", curState.data.status.size.toString())
                    Log.d("Размерность списка полученных вакансий", curState.data.information.size.toString())
                    for (i in 0 until curState.data.status.size) {
                        ResponseVacancyCard(
                            list = curState.data.information[i],
                            status = curState.data.status[i].otvet
                        )
                    }
                }
                ScreenState.Error -> AlertText()
            }
        }
    }

}

@Composable
private fun MainBody(vacancies: ResponsesUIModel)
{


}