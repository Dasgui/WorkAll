package com.example.ozonchick_new_version.Presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ozonchick_new_version.Presentation.ui.text_tema.Tema
import com.example.ozonchick_new_version.Presentation.ui.theme.BackgroundColor
import com.example.ozonchick_new_version.Presentation.ui.theme.MainColor
import io.github.jan.supabase.realtime.Column

@Composable
fun FailAlertDialog(
    title: String,
    text: String,
    onOkClick: () -> Unit,
) {

    Dialog(
        onDismissRequest = onOkClick,
    ) {
        CustomCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(5.dp),
            shape = RoundedCornerShape(25.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
            ) {
                Text(text = title, style = Tema.Text_24_24_Medium, color = MainColor)
                VerticalSpacer(10.dp)
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = text,
                        style = Tema.Text_20_20_Medium,
                        color = MainColor,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        "Хорошо",
                        style = Tema.Text_20_20_Medium,
                        color = MainColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onOkClick()
                            },
                        textAlign = TextAlign.Right,
                    )
                }
            }
        }
    }

//    AlertDialog(
//        containerColor = BackgroundColor,
//        title = {
//            Text(text = title, style = Tema.Text_24_24_Medium, color = MainColor)
//        },
//        text = {
//            Text(text = text, style = Tema.Text_20_20_Medium, color = MainColor, modifier = Modifier.fillMaxWidth())
//        },
//        onDismissRequest = {
//            onOkClick()
//        },
//        confirmButton = {
//            TextButton(
//                onClick = {
//                    onOkClick()
//                }
//            ) {
//                Text("Хорошо", style = Tema.Text_20_20_Medium, color = MainColor)
//            }
//        },
//
//    )
}

@Composable
fun FailAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String = "Вы хотите продолжить?",
    dialogText: String,
    dialogConfirmText : String = "Подтверждаю",
    dialogDismissText : String = "Отказываюсь"
) {

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        CustomCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            shape = RoundedCornerShape(25.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Top,
            ) {
                Text(text = dialogTitle, style = Tema.Text_24_24_Medium, color = MainColor)
                VerticalSpacer(10.dp)
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = dialogText,
                        style = Tema.Text_20_20_Medium,
                        color = MainColor,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            dialogDismissText,
                            style = Tema.Text_18_18_Medium,
                            color = MainColor,
                            modifier = Modifier
                                .clickable {
                                    onDismissRequest()
                                },
                        )

                        Text(
                            dialogConfirmText,
                            style = Tema.Text_18_18_Medium,
                            color = MainColor,
                            modifier = Modifier
                                .clickable {
                                    onConfirmation()
                                },
                        )
                    }
                }
            }
        }
    }
}