package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.ozonchick_new_version.Data.model.Authentication.Candidate.Candidate
import com.example.ozonchick_new_version.Data.model.Candidate_information
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Presentation.Screens.ContactsScreen
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor

@Composable
fun CandidateCard(
    candidate: Candidate,
    acceptCandidate: () -> Unit,
    rejectCandidate: () -> Unit,
    navigateOnDescription: () -> Unit
) {
    CustomCard {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            MainText(
                candidate
            )
            MainButtons(
                acceptCandidate = {acceptCandidate()},
                navigateOnDescription = {navigateOnDescription()},
                rejectCandidate = {rejectCandidate()}
            )
        }
    }
}

@Composable
private fun MainText(
    candidate: Candidate
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        //Имя пользователя
        Text(
            text = candidate.name,
            style = Tema.Text_24_24_Bold,
            color = MainColor
        )
        //Имя пользователя
        Text(
            text = candidate.name,
            style = Tema.Text_24_24_Bold,
            color = MainColor
        )
    }
}

@Composable
private fun MainButtons(
    acceptCandidate: () -> Unit,
    navigateOnDescription: () -> Unit,
    rejectCandidate: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        CustomButton(
            onClick = {
                acceptCandidate()
            },
            placeText = "Принять",
            modifier = Modifier.fillMaxWidth(),
            reverseColors = true
        )
        CustomButton(
            onClick = {
                navigateOnDescription()
            },
            placeText = "Подробная информация",
            modifier = Modifier.fillMaxWidth(),
            reverseColors = true
        )
        CustomButton(
            onClick = {
                rejectCandidate()
            },
            placeText = "Отклонить",
            modifier = Modifier.fillMaxWidth()
        )
    }
}