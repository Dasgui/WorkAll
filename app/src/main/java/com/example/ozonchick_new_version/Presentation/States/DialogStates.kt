package com.example.ozonchick_new_version.Presentation.States

sealed interface DialogStates {
    object Error : DialogStates
    object None : DialogStates
    object Okay : DialogStates
}