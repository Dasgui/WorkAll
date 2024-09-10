package com.example.ozonchick_new_version.Screens

import com.example.ozonchick_new_version.Presentation_Layer.Models.HomeViewModel
import com.example.ozonchick_new_version.Presentation_Layer.Models.ListOfCandidate_ViewModel
import com.example.ozonchick_new_version.Presentation_Layer.Models.ListOfVacancies_ViewModel
import com.example.ozonchick_new_version.Presentation_Layer.Models.Send_a_Package_ViewModel
import com.example.ozonchick_new_version.Presentation_Layer.Models.SignUpViewModel
import com.example.ozonchick_new_version.Presentation_Layer.Models.Spisok_ViewModel
import com.example.ozonchick_new_version.Presentation_Layer.Models.VakansCreate_ViewModel
import org.koin.dsl.module

var AppModule = module {
    single {
        SignUpViewModel()

    }
    single {
        HomeViewModel()
    }
    single {
        Send_a_Package_ViewModel()
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