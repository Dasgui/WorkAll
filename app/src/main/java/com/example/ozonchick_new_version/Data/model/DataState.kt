package com.example.ozonchick_new_version.Data.model

sealed class DataState {
    class Success(val data:MutableList<Vakans_information>):DataState()
    class Failure(val message:String):DataState()
    object Loading:DataState()
    object Empty:DataState()
}