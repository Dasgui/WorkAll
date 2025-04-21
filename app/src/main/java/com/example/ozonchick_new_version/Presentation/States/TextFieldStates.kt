package com.example.ozonchick_new_version.Presentation.States

sealed interface TextFieldStates {
    object Password : TextFieldStates
    object Email : TextFieldStates
    object Normal : TextFieldStates
    object Salary : TextFieldStates
}