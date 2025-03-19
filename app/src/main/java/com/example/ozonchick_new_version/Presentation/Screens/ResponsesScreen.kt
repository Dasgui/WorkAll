package com.example.ozonchick_new_version.Presentation.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.ozonchick_new_version.Data.data.db.idSharedUser
import com.example.ozonchick_new_version.Presentation.ViewModels.ResponsesViewModel
import com.example.ozonchick_new_version.Presentation.ui.components.Public_Loading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.Responses.VacancyCard
import com.example.ozonchick_new_version.Presentation.ui.theme.Ozonchick_new_versionTheme
import org.koin.androidx.compose.koinViewModel

class ResponsesScreen : Screen {
    @Composable
    override fun Content() {
        Ozonchick_new_versionTheme {
            ResponseConstruct()
        }

    }
}

@Composable
private fun ResponseConstruct(viewModel: ResponsesViewModel = koinViewModel())
{
    DisposableEffect(Unit) {
        onDispose {
            viewModel.switchIsLoading()
            viewModel.clearResponses()
            viewModel.clearIdVacancy()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getRespones(idSharedUser.value)
        viewModel.getVacancy(viewModel.idVacancy.value)
        viewModel.isLoading.value = false
    }

    MainBody(isLoading = viewModel.isLoading.value)

}


@Composable
private fun MainBody(viewModel: ResponsesViewModel = koinViewModel(), isLoading:Boolean)
{
    CustomScaffold(
        bottomButton = true,
        mainText = "Отклики"
    ) {
        if (isLoading) {
            Public_Loading(isLoading = isLoading)
        } else {
            LazyColumn(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .padding(top = 70.dp, bottom = 80.dp, start = 15.dp, end = 15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Companion.CenterHorizontally
            ) {
                items(viewModel.listResponses.value.size)
                { item ->
                    VacancyCard(viewModel.listResponses.collectAsState().value[item])
                }
            }
        }
    }

}