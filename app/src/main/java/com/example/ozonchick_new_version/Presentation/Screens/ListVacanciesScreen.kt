package com.example.ozonchick_new_version.Presentation.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Presentation.Screens.Profile.ProfileScreen
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ViewModels.ResponsesViewModel
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.Responses.VacancyCard
import com.example.ozonchick_new_version.Presentation.ui.theme.Ozonchick_new_versionTheme
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
private fun ListVacanciesScreenConstruct(viewModel: ResponsesViewModel = koinViewModel()) {
    val screenState = viewModel.responseState.collectAsState()
    when (val curState = screenState.value) {
        is ScreenState.Failure -> FailAlertDialog(
            title = "Ошибка входа",
            curState.message,
            onOkClick = {
                viewModel.responseState.value = ScreenState.Loading
            })

        is ScreenState.Loading -> CustomLoading()
        is ScreenState.Success -> MainScreen(viewModel)
    }
}

@Composable
private fun MainScreen(viewModel: ResponsesViewModel) {
    CustomScaffold(mainText = "Вакансии", bottomButton = true) {
        Column (
            modifier = Modifier.Companion
                .padding(bottom = 80.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            viewModel.listVacancy.collectAsState().value.forEach { item ->
                VacancyCard(item)
            }
        }
    }
}