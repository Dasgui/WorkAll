package com.example.ozonchick_new_version.Presentation.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class VakansCreate_ViewModel : ViewModel() {
    var specialization: MutableState<String> = mutableStateOf("");
    var wage: MutableState<String> = mutableStateOf("");
    var phoneNumber: MutableState<String> = mutableStateOf("");
    var mail: MutableState<String> = mutableStateOf("");
    var locationOfWork: MutableState<String> = mutableStateOf("");
    var experience: MutableState<String> = mutableStateOf("");
}