package com.example.ozonchick_new_version.Presentation.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.ozonchick_new_version.Presentation.ViewModels.ResponsesViewModel
import com.example.ozonchick_new_version.Presentation.ui.components.Public_Loading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.Responses.VacancyCard
import com.example.ozonchick_new_version.Presentation.ui.theme.Ozonchick_new_versionTheme
import org.koin.androidx.compose.koinViewModel

class ListVacanciesScreen() : Screen {
    @Composable
    override fun Content() {
        Ozonchick_new_versionTheme {
            Surface(
                modifier = Modifier.Companion.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ListVacanciesScreenConstruct()
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ListVacanciesScreenConstruct(viewModel: ResponsesViewModel = koinViewModel()) {
    DisposableEffect(Unit) {
        onDispose {
            viewModel.switchIsLoading()
        }
    }
    LaunchedEffect(viewModel.listVacancy.collectAsState().value.isEmpty()) {
        viewModel.getInformation()
        viewModel.isLoading.value = false
    }
    MainScreen(isLoading = viewModel.isLoading.value)


}

@Composable
private fun MainScreen(viewModel: ResponsesViewModel = koinViewModel(), isLoading: Boolean) {
    CustomScaffold(mainText = "Вакансии", bottomButton = true) {
        if (isLoading) {
            Public_Loading(isLoading = isLoading)
        } else {
            LazyColumn(
                modifier = Modifier.Companion
                    .padding(top = 70.dp, bottom = 80.dp, start = 15.dp, end = 15.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Companion.CenterHorizontally
            ) {
                items(viewModel.listVacancy.value.size) { item ->
                    VacancyCard(viewModel.listVacancy.collectAsState().value[item])
                }

            }
        }
    }
}