package com.example.ozonchick_new_version.Presentation

import com.example.ozonchick_new_version.Presentation.ViewModels.ContactsViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.HomeViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.ListOfCandidate_ViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.LogInViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.VakansCreate_ViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.ProfileViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.ResponsesViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var AppModule = module {
    single {
        LogInViewModel()
    }
    viewModel {
        SignUpViewModel()
    }

    viewModel {
        (userId : Int) ->
        ProfileViewModel(userId)
    }

    viewModel {
        HomeViewModel()
    }
    viewModel {
        ResponsesViewModel()
    }
    viewModel {
        ContactsViewModel()
    }

/*    viewModel {
        ListOfVacancies_ViewModel()
    }*/
    viewModel {
        ListOfCandidate_ViewModel()
    }
    viewModel {
        VakansCreate_ViewModel()
    }
}