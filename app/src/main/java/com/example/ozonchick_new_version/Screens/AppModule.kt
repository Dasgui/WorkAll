package com.example.ozonchick_new_version.Screens

import com.example.ozonchick_new_version.Screens.Contacts.ContactsViewModel
import com.example.ozonchick_new_version.Screens.Home.HomeViewModel
import com.example.ozonchick_new_version.Screens.Home.Employer.Candidate.ListOfCandidate_ViewModel
import com.example.ozonchick_new_version.Screens.Home.Employer.Vacancies.ListOfVacancies_ViewModel
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.LogIn.LogInViewModel
import com.example.ozonchick_new_version.Screens.Home.Candidate.VakansCreate_ViewModel
import com.example.ozonchick_new_version.Screens.Responses.ResponsesViewModel
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.SignUp.SignUpViewModel
import org.koin.dsl.module

var AppModule = module {
    single {
        LogInViewModel()
    }
    single {
        SignUpViewModel()
    }


    single {
        HomeViewModel()
    }
    single {
        ResponsesViewModel()
    }
    single {
        ContactsViewModel()
    }

    single {
        ListOfVacancies_ViewModel()
    }
    single {
        ListOfCandidate_ViewModel()
    }
    single {
        VakansCreate_ViewModel()
    }


}