package com.example.ozonchick_new_version.Presentation.Screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data.model.Ktor.VacancyResponse
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ViewModels.ContactsViewModel
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.RowTextImage
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.Ozonchick_new_versionTheme
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import com.example.ozonchick_new_version.R
import org.koin.androidx.compose.get
import org.koin.core.parameter.parametersOf

var isLoading: MutableState<Boolean> = mutableStateOf(false)

class ContactsScreen (private val sharedId: Int) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ContactsViewModel = get {parametersOf(sharedId)}
        Ozonchick_new_versionTheme {
            Contacts_construct(viewModel)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Contacts_construct(viewModel: ContactsViewModel) {
    val navigator = LocalNavigator.current
    val screenState = viewModel.contactsState.collectAsState()
    CustomScaffold(
        mainText = "Контактная информация",
        bottomButton = true,
        backButton = true,
        scaffoldStates = ScaffoldStates.OnSide,
        mainBody = {
            when (val curState = screenState.value) {
                is ScreenState.Failure -> FailAlertDialog(
                    title = "Ошибка",
                    curState.message,
                    onOkClick = {
                        viewModel.contactsState.value = ScreenState.Error
                    })
                is ScreenState.Loading -> CustomLoading()
                is ScreenState.Success -> SuccessBody(curState.data)

                is ScreenState.Error -> AlertText()
                ScreenState.AwaitingChangeFromUser -> Unit
                ScreenState.AwaitingResponse -> Unit
            }
        }
    )

}

@Composable
private fun SuccessBody(data: VacancyResponse) {
    Column (
        modifier = Modifier
            .padding(bottom = 80.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        RowTextImage(
            R.drawable.phone_fill_icon,
            title = "Номер телефона:",
            text = data.vacancyInfo.phoneNumber,
            isDescription = true
        )
        RowTextImage(
            R.drawable.email_fill_icon,
            title = "Почта:",
            text = data.vacancyInfo.email,
            isDescription = true
        )
        RowTextImage(
            R.drawable.town_icon,
            title = "Расположение:",
            text = data.vacancyInfo.location,
            isDescription = true
        )
        Canvas(modifier = Modifier.fillMaxWidth()) {
            drawLine(
                color = MainColor,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 15f
            )
        }
        VerticalSpacer(20.dp)
        RowTextImage(
            R.drawable.information_icon,
            title = "О работе: ",
            data.vacancyInfo.aboutWork,
            isDescription = true
        )
    }
}