package com.example.ozonchick_new_version.Presentation

sealed interface LogInState <out T : Any> {
    data class SuccessLogIn <out T : Any> (val data : T) : LogInState <T>
    data class Failure (
        val code : Int,
        val message : String
    ) : LogInState <Nothing>
}