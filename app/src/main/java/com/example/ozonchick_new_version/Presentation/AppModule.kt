package com.example.ozonchick_new_version.Presentation

import com.example.ozonchick_new_version.Data.network.Repository
import com.example.ozonchick_new_version.Data.network.RepositoryImpl
import com.example.ozonchick_new_version.Domain.AuthUseCase
import com.example.ozonchick_new_version.Presentation.ViewModels.ContactsViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.HomeViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.ListOfCandidate_ViewModel
import com.example.ozonchick_new_version.Presentation.Screens.LogIn.LogInViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.VakansCreate_ViewModel
import com.example.ozonchick_new_version.Presentation.Screens.Profile.ProfileViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.ResponsesViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var AppModule = module {
    factory<Repository> { RepositoryImpl() }
    factory { AuthUseCase(get()) }
    viewModel {
        LogInViewModel(get())
    }

    viewModel {
        SignUpViewModel()
    }

    viewModel {
        ProfileViewModel()
//        (userId : Int) ->
//        ProfileViewModel(userId)
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