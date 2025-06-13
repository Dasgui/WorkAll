package com.example.ozonchick_new_version.Presentation.Screens.Search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.example.ozonchick_new_version.Data.model.Ktor.Experience
import com.example.ozonchick_new_version.Data.model.ListOfVacancies.StatesOfGetVacancies
import com.example.ozonchick_new_version.Presentation.Screens.ListVacancies.ListVacanciesScreen
import com.example.ozonchick_new_version.Presentation.States.ScaffoldStates
import com.example.ozonchick_new_version.Presentation.States.ScreenState
import com.example.ozonchick_new_version.Presentation.States.ScreenState.AwaitingChangeFromUser
import com.example.ozonchick_new_version.Presentation.States.TextFieldStates
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
import com.example.ozonchick_new_version.Presentation.ui.theme.SecondColor
import com.example.ozonchick_new_version.R
import org.koin.androidx.compose.koinViewModel

class SearchScreen(private val statesOfGetVacancies: StatesOfGetVacancies = StatesOfGetVacancies.Normal) :
    Screen {
    @Composable
    override fun Content() {
        SearchConstruct(statesOfGetVacancies = statesOfGetVacancies)
    }
}

@Composable
private fun SearchConstruct(
    viewModel: SearchViewModel = koinViewModel(),
    statesOfGetVacancies: StatesOfGetVacancies
) {
    val navigator = LocalNavigator.current
    LaunchedEffect(true) {
        when (val localState = statesOfGetVacancies) {
            is StatesOfGetVacancies.Filter -> {
                viewModel.loadFilters(localState)
            }

            StatesOfGetVacancies.Normal -> viewModel.loadExperience()
        }

    }
    CustomScaffold(
        mainText = "Поиск",
        backButton = true,
        scaffoldStates = ScaffoldStates.Vacancies
    ) {
        val localState = viewModel.screenState.collectAsState()
        when (val curState = localState.value) {
            ScreenState.AwaitingChangeFromUser -> Unit
            ScreenState.AwaitingResponse -> Unit
            ScreenState.Error -> AlertText()
            is ScreenState.Failure -> {
                FailAlertDialog(
                    title = "Ошибка",
                    curState.message,
                    onOkClick = {
                        viewModel.screenState.value = AwaitingChangeFromUser
                    })
            }

            ScreenState.Loading -> CustomLoading()
            is ScreenState.Success<*> -> Unit
        }
//        SearchType(viewModel)
        SearchField(viewModel)
        FilterFields(viewModel)
//        if (viewModel.searchVisible.value) {
//            SearchField(viewModel)
//        } else {
//            FilterFields(viewModel)
//        }
//        SearchField(viewModel)
        VerticalSpacer(25.dp)
        BottomButtons(viewModel, navigator)
    }
}

@Composable
private fun SearchType(viewModel: SearchViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Поиск",
            style = Tema.Text_24_24_Bold,
            color = if (viewModel.searchVisible.value) {
                MainColor
            } else {
                SecondColor
            },
            modifier = Modifier.clickable {
                viewModel.searchVisible.value = true
//                viewModel.selectedExperience.value = null
//                viewModel.minSalaryText.value = ""
//                viewModel.checkExperience.value = false
//                viewModel.checkSalary.value = false
            })
        Text(
            text = "Фильтры",
            style = Tema.Text_24_24_Bold,
            color = if (!viewModel.searchVisible.value) {
                MainColor
            } else {
                SecondColor
            },
            modifier = Modifier.clickable {
                viewModel.searchVisible.value = false
//                viewModel.search.value = ""
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchField(viewModel: SearchViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Text(
            text = "Поиск",
            style = Tema.Text_20_20_Medium,
            color = MainColor
        )
        VerticalSpacer(10.dp)
        CustomOutlinedTextField(
            value = viewModel.search.value,
            onValueChange = {
                viewModel.search.value = it
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun FilterFields(viewModel: SearchViewModel) {
    VerticalSpacer(20.dp)
    FilterField(
        viewModel.checkSalary,
        viewModel.minSalaryText,
        title = "Учитывать начальный уровень зарплаты",
        image = R.drawable.wallet_3
    )
    VerticalSpacer(20.dp)
    FilterDropMenu(
        viewModel.checkExperience,
        title = "Учитывать требуемый опыт работы",
        selectedExperience = viewModel.selectedExperience.value,
        experienceList = viewModel.experienceList.value,
        selectedChange = {
            viewModel.selectedExperience.value = it
        }
    )
}

@Composable
private fun FilterField(
    checked: MutableState<Boolean>,
    filter: MutableState<String>,
    title: String, image: Int,
) {
    CheckBoxField(
        checked = checked.value,
        onChange = {
            checked.value = it
        },
        text = title
    )
    VerticalSpacer(15.dp)
    if (checked.value) {
        CustomOutlinedTextField(
            value = filter.value,
            states = TextFieldStates.Salary,
            trailingIcon = {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MainColor),
                    modifier = Modifier.size(30.dp)
                )
            },
            onValueChange = {
                filter.value = it
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterDropMenu(
    checked: MutableState<Boolean>,
    selectedExperience: Experience?,
    experienceList: List<Experience>,
    selectedChange: (Experience) -> Unit,
    title: String,

    ) {
    CheckBoxField(
        checked = checked.value,
        onChange = {
            checked.value = it
        },
        text = title
    )
    VerticalSpacer(15.dp)
    if (checked.value) {
        CustomComboBox(
            textField = selectedExperience?.name ?: "Выберите значение",
            dropItemsList = experienceList,
            selectedChange = { item ->
                selectedChange(item)

            },
            itemToString = { it.name }
        )

//        CustomComboBox(
//            textField = textField.value,
//            dropItemsList = dropItemsList.value,
//            selectedChange = { item ->
//                textField.value = item
//                visibleList.value = false
//            },
//            itemToString = {it}
//        )

//        CustomOutlinedTextField(
//            value = textField.value,
//            states = TextFieldStates.None,
//            onValueChange = {},
//            readOnly = true,
//            trailingIcon = {
//                Icon(
//                    painter = painterResource(id = R.drawable.arrow_square_down),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(30.dp)
//                        .clickable { visibleList.value = true },
//                    tint = MainColor
//                )
//                DropdownMenu(
//                    expanded = visibleList.value,
//                    onDismissRequest = {
//                        visibleList.value = false
//                    },
//                    containerColor = BackgroundColor,
//                ) {
//                    for (item in 0 until dropItemsList.value.size) {
//                        if (item != 0) HorizontalDivider()
//                        DropdownMenuItem(
//                            text = {
//                                Text(
//                                    dropItemsList.value[item],
//                                    style = Tema.Text_16_16_Medium,
//                                    color = MainColor
//                                )
//                            },
//                            onClick = {
//                                textField.value = dropItemsList.value[item]
//                                visibleList.value = false
//                            },
//                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
//                        )
//                    }
//                }
//            },
//        )
    }
}


@Composable
fun BottomButtons(viewModel: SearchViewModel, navigator: Navigator?) {
    CustomButton(
        onClick = {
            navigator?.replaceAll(
                ListVacanciesScreen(
                    viewModel.getStatesOfGetVacancies()
                )
            )
        },
        placeText = "Найти вакансии"
    )
    VerticalSpacer(10.dp)
    CustomButton(
        onClick = {
            viewModel.resetFilters()
            navigator?.replaceAll(
                ListVacanciesScreen(
                    viewModel.getStatesOfGetVacancies()
                )
            )
//            navigator?.replaceAll(
//                ListVacanciesScreen(
//                    StatesOfGetVacancies.Normal
//                )
//            )
        },
        placeText = "Сбросить поиск"
    )
}