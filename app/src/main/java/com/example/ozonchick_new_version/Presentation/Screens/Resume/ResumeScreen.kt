package com.example.ozonchick_new_version.Presentation.Screens.Resume

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Presentation.Models.ResumeUIModel
import com.example.ozonchick_new_version.Presentation.Screens.Profile.ProfileScreen
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.ui.components.AlertText
import com.example.ozonchick_new_version.Presentation.ui.components.CheckBoxField
import com.example.ozonchick_new_version.Presentation.ui.components.CustomButton
import com.example.ozonchick_new_version.Presentation.ui.components.CustomComboBox
import com.example.ozonchick_new_version.Presentation.ui.components.CustomLoading
import com.example.ozonchick_new_version.Presentation.ui.components.CustomOutlinedTextField
import com.example.ozonchick_new_version.Presentation.ui.components.CustomScaffold
import com.example.ozonchick_new_version.Presentation.ui.components.FailAlertDialog
import com.example.ozonchick_new_version.Presentation.ui.components.VerticalSpacer
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import org.koin.androidx.compose.koinViewModel

class ResumeScreen : Screen {
    @Composable
    override fun Content() {
        ResumeConstruct()
    }
}

@Composable
private fun ResumeConstruct(viewModel: ResumeViewModel = koinViewModel()) {
    val navigator = LocalNavigator.current
    val screenState = viewModel.screenState.collectAsState()
    LaunchedEffect(true) {
        viewModel.loadData()
    }
    CustomScaffold(
        scaffoldStates = ScaffoldStates.OnSide,
        mainText = "Резюме"
    ) {
        when (val localState = screenState.value) {
            ScreenState.AwaitingChangeFromUser -> {
                if (viewModel.isFieldsError.value) {
                    FailAlertDialog(
                        title = "Ошибка",
                        text = "Заполните поля или выбирите один из вариантов.",
                        onOkClick = { viewModel.isFieldsError.value = false }
                    )
                }
                when (viewModel.stepScreen.value) {
                    ResumeUIModel.Education -> Education(viewModel, navigator)
                    ResumeUIModel.Experience -> Experience(viewModel)
                    ResumeUIModel.Information -> ResumeInformation(viewModel, navigator)
                }
            }

            ScreenState.AwaitingResponse -> Unit
            ScreenState.Error -> AlertText()
            is ScreenState.Failure -> {
                FailAlertDialog(
                    title = "Ошибка",
                    localState.message,
                    onOkClick = {
                        viewModel.screenState.value = ScreenState.AwaitingChangeFromUser
                    })
            }

            ScreenState.Loading -> CustomLoading()
            is ScreenState.Success<*> -> {
                FailAlertDialog(
                    title = "Успех",
                    "Резюме было обновленно/добавленно",
                    onOkClick = {
                        navigator?.replaceAll(ProfileScreen())
                    })
            }
        }
    }
}


@Composable
private fun Education(viewModel: ResumeViewModel, navigator: Navigator?) {
    Text(
        text = "Какое у вас образование?",
        style = Tema.Text_20_20_Medium,
        color = MainColor
    )
    VerticalSpacer(10.dp)
    CustomComboBox(
        textField = viewModel.selectedEducation.value?.name ?: "Выберите значение",
        dropItemsList = viewModel.educationList.value,
        selectedChange = { education ->
            viewModel.selectedEducation.value = education
        },
        itemToString = {it.name}
    )
    VerticalSpacer(20.dp)
    BottomButtons(
        topButtonClick = {
            if (viewModel.checkFields()) {
                viewModel.switchStep()
            } else {
                viewModel.isFieldsError.value = true
            }
        },
        bottomButtonClick = {
            viewModel.switchStep(
                isBack = true,
                navigateOnPreviousScreen = { navigator?.pop() })
        }
    )
}

@Composable
private fun Experience(viewModel: ResumeViewModel) {
    Text(
        text = "Вы раньше где-то работали?",
        style = Tema.Text_20_20_Medium,
        color = MainColor,
        modifier = Modifier.fillMaxWidth(),
    )
    VerticalSpacer(20.dp)
    CheckBoxField(
        checked = viewModel.enabledFieldsExperience.value,
        onChange = {
            viewModel.enabledFieldsExperience.value = it
        },
        text = "Да"
    )
    VerticalSpacer(30.dp)
    if (viewModel.enabledFieldsExperience.value) {


        Text(
            text = "Укажите кем вы раньше работали",
            style = Tema.Text_20_20_Medium,
            color = MainColor
        )
        VerticalSpacer(5.dp)

        CustomOutlinedTextField(
            value = viewModel.professionName.value,
            onValueChange = {
                if (viewModel.professionName.value.length < viewModel.professionalMaxChars.value) {
                    viewModel.professionName.value = it
                }
            },
            enabled = viewModel.enabledFieldsExperience.value
        )
        VerticalSpacer(10.dp)

        Text(
            text = "Сколько вы проработали?",
            style = Tema.Text_20_20_Medium,
            color = MainColor
        )
        VerticalSpacer(10.dp)
        CustomComboBox(
            textField = viewModel.selectedExperience.value?.name ?: "Выберите значение",
            dropItemsList = viewModel.experienceList.value,
            selectedChange = { experience ->
                viewModel.selectedExperience.value = experience
            },
            enabled = viewModel.enabledFieldsExperience.value,
            itemToString = { it.name }
        )
        VerticalSpacer(80.dp)
    }


    BottomButtons(
        topButtonTitle = "Далее",
        bottomButtonTitle = "Назад",
        topButtonClick = {
            if (viewModel.checkFields()) {
                viewModel.switchStep()
            } else {
                viewModel.isFieldsError.value = true
            }
        },
        bottomButtonClick = {
            viewModel.switchStep(isBack = true)
        },
    )
}

@Composable
private fun ResumeInformation(viewModel: ResumeViewModel, navigator: Navigator?) {
    Text(
        text = "Расскажите о себе",
        style = Tema.Text_20_20_Medium,
        color = MainColor
    )
    VerticalSpacer(10.dp)
    CustomOutlinedTextField(
        value = viewModel.information.value,
        onValueChange = {
            if (viewModel.information.value.length < viewModel.informationMaxChars.value) {
                viewModel.information.value = it
            }
        },
        singleLine = false,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
    VerticalSpacer(20.dp)
    Text(
        text = "${viewModel.information.value.length}/${viewModel.informationMaxChars.value}",
        style = Tema.Text_18_18_Medium,
        color = MainColor
    )
    VerticalSpacer(50.dp)
    BottomButtons(
        topButtonTitle = "Сохранить резюме",
        topButtonClick = {
            if (viewModel.checkFields()) {
                viewModel.screenState.value = ScreenState.Loading
                viewModel.postResume()
            } else {
                viewModel.isFieldsError.value = true
            }
        },
        bottomButtonClick = {
            viewModel.switchStep(isBack = true)
        }
    )
}

@Composable
fun BottomButtons(
    topButtonTitle: String = "Далее", bottomButtonTitle: String = "Назад",
    topButtonClick: () -> Unit, bottomButtonClick: () -> Unit
) {
    CustomButton(
        onClick = {
            topButtonClick()
        },
        placeText = topButtonTitle
    )
    VerticalSpacer(10.dp)
    CustomButton(
        onClick = {
            bottomButtonClick()
        },
        placeText = bottomButtonTitle
    )
}