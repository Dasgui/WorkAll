package com.example.ozonchick_new_version.Screens

import com.example.ozonchick_new_version.Screens.Home.HomeViewModel
import com.example.ozonchick_new_version.Screens.Home.Employer.Candidate.ListOfCandidate_ViewModel
import com.example.ozonchick_new_version.Screens.Home.Employer.Vacancies.ListOfVacancies_ViewModel
import com.example.ozonchick_new_version.Screens.SignUp_LogIn.SignUpViewModel
import com.example.ozonchick_new_version.Screens.Otkliks.Spisok_ViewModel
import com.example.ozonchick_new_version.Screens.Home.Candidate.VakansCreate_ViewModel
import org.koin.dsl.module

var AppModule = module {
    single {
        SignUpViewModel()

    }
    single {
        HomeViewModel()
    }
    single {
        Spisok_ViewModel()
    }
    single {
        VakansCreate_ViewModel()
    }
    single {
        ListOfVacancies_ViewModel()
    }
    single {
        ListOfCandidate_ViewModel()
    }
}