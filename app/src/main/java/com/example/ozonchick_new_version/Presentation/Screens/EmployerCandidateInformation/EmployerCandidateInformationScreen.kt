package com.example.ozonchick_new_version.Presentation.Screens.EmployerCandidateInformation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.ozonchick_new_version.Data.model.Ktor.ResumeData
import com.example.ozonchick_new_version.Data.model.Resume.ResumeDto
import com.example.ozonchick_new_version.Presentation.Models.ExclusionOptions
import com.example.ozonchick_new_version.Presentation.Screens.EmployerResponses.EmployerResponsesViewModel
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.NameTextField
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

class EmployerCandidateInformationScreen(val candidateId : Int) : Screen {
    @Composable
    override fun Content() {
        val viewModel: EmployerCandidateInformationViewModel = get { parametersOf(candidateId) }
        EmployerCandidateInformationConstruct(viewModel)
    }
}

@Composable
private fun EmployerCandidateInformationConstruct(viewModel: EmployerCandidateInformationViewModel) {
    LaunchedEffect(true) {
        viewModel.getResume()
    }
    val localState = viewModel.screenState.collectAsState()
    CustomScaffold (
        mainText = "Информация",
        scaffoldStates = ScaffoldStates.OnSide,
        backButton = true
    ) {
        when (val localState = localState.value) {
            ScreenState.AwaitingChangeFromUser -> AlertText(ExclusionOptions.NullResume.message)
            ScreenState.AwaitingResponse -> Unit
            ScreenState.Error -> AlertText()
            is ScreenState.Failure -> {
                FailAlertDialog(
                    title = "Ошибка",
                    localState.message,
                    onOkClick = {
                        viewModel.screenState.value = ScreenState.Error
                    })
            }
            ScreenState.Loading -> CustomLoading()
            is ScreenState.Success<ResumeData> -> SuccessBody(localState.data)
        }
    }
}

@Composable
private fun SuccessBody (
    information : ResumeData
) {
//    TextUnderText(
//        title = "Оброзование :",
//        text = information.education
//    )
//    NameTextField(
//        title = "Оброзование :",
//        spaceBy = 10.dp,
//        information = information.education,
//        enabled = false
//    )
//    VerticalSpacer(40.dp)
//    Text(
//        text = "Кем раньше работал человек",
//        style = Tema.Text_24_24_Bold,
//        color = MainColor
//    )
//    VerticalSpacer(20.dp)
////    TextUnderText(
////        title = "Название професии :",
////        text = information.professionName
////    )
//    NameTextField(
//        title = "Название професии :",
//        spaceBy = 10.dp,
//        information = information.professionName,
//        enabled = false
//    )
//    VerticalSpacer(10.dp)
////    TextUnderText(
////        title = "Сколько проработал :",
////        text = information.experience
////    )
    NameTextField(
        title = "Имя :",
        spaceBy = 10.dp,
        information = information.candidateInfo.name,
        enabled = false,
        singleLine = false
    )
    VerticalSpacer(20.dp)
    NameTextField(
        title = "Номер телефона :",
        spaceBy = 10.dp,
        information = information.candidateInfo.phoneNumber,
        enabled = false,
        singleLine = false
    )
    VerticalSpacer(20.dp)
    NameTextField(
        title = "Почта :",
        spaceBy = 10.dp,
        information = information.candidateInfo.email,
        enabled = false,
        singleLine = false
    )

    VerticalSpacer(40.dp)

    NameTextField(
        title = "Опыт :",
        spaceBy = 10.dp,
        information = information.resumesInfo?.firstOrNull()?.experienceInfo?.name ?: "",
        enabled = false
    )
    VerticalSpacer(20.dp)
    NameTextField(
        title = "Как человек описывает себя :",
        spaceBy = 10.dp,
        information = information.resumesInfo?.firstOrNull()?.description ?: "",
        enabled = false,
        singleLine = false
    )
}

@Composable
fun TextUnderText (
    title : String,
    text : String
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = title,
            style = Tema.Text_24_24_Bold,
            color = MainColor
        )
        Text(
            text = text,
            style = Tema.Text_24_24_Bold,
            color = MainColor
        )
    }
}