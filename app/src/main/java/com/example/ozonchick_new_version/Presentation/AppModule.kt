package com.example.ozonchick_new_version.Presentation

import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.network.Repository
import com.example.ozonchick_new_version.Data.network.RepositoryImpl
import com.example.ozonchick_new_version.Domain.AcceptCandidateUseCase
import com.example.ozonchick_new_version.Domain.AcceptVacancyUseCase
import com.example.ozonchick_new_version.Domain.AddVacancyUseCase
import com.example.ozonchick_new_version.Domain.AuthUseCase
import com.example.ozonchick_new_version.Domain.DeleteEmployerVacancyUseCase
import com.example.ozonchick_new_version.Domain.GetContactInformationUseCase
import com.example.ozonchick_new_version.Domain.GetEmployerVacanciesUseCase
import com.example.ozonchick_new_version.Domain.GetInformationResumeUseCase
import com.example.ozonchick_new_version.Domain.GetNameUseCase
import com.example.ozonchick_new_version.Domain.GetOrganizationNameUseCase
import com.example.ozonchick_new_version.Domain.GetResponsesIdVacanciesUseCase
import com.example.ozonchick_new_version.Domain.GetResponsesVacanciesUseCase
import com.example.ozonchick_new_version.Domain.GetResumeCandidateUseCase
import com.example.ozonchick_new_version.Domain.GetUserChangeableInformationUseCase
import com.example.ozonchick_new_version.Domain.GetUsersForEmployerUseCase
import com.example.ozonchick_new_version.Domain.GetVacanciesUseCase
import com.example.ozonchick_new_version.Domain.GetVacancyAllInformationUseCase
import com.example.ozonchick_new_version.Domain.RegistrationCandidateUseCase
import com.example.ozonchick_new_version.Domain.RegistrationEmployerUseCase
import com.example.ozonchick_new_version.Domain.RegistrationUseCase
import com.example.ozonchick_new_version.Domain.RejectCandidateUseCase
import com.example.ozonchick_new_version.Domain.RejectVacancyUseCase
import com.example.ozonchick_new_version.Domain.SearchVacanciesUseCase
import com.example.ozonchick_new_version.Domain.UpdateEmployerVacancyUseCase
import com.example.ozonchick_new_version.Domain.UpdateResumeCandidateUseCase
import com.example.ozonchick_new_version.Domain.UpdateUserInformationUseCase
import com.example.ozonchick_new_version.Presentation.Models.EmployerDetailsVacancy
import com.example.ozonchick_new_version.Presentation.Screens.ChangeInformation.ChangeInformationViewModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerAddVacancy.EmployerAddVacancyScreen
import com.example.ozonchick_new_version.Presentation.Screens.EmployerAddVacancy.EmployerAddVacancyViewModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerCandidateInformation.EmployerCandidateInformationViewModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy.EmployerDetailsVacancyScreen
import com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy.EmployerDetailsVacancyViewModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerResponses.EmployerResponsesViewModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies.EmployerVacanciesViewModel
import com.example.ozonchick_new_version.Presentation.Screens.ListVacancies.ListVacanciesViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.ContactsViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.ListOfCandidate_ViewModel
import com.example.ozonchick_new_version.Presentation.Screens.LogIn.LogInViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.VakansCreate_ViewModel
import com.example.ozonchick_new_version.Presentation.Screens.Profile.ProfileViewModel
import com.example.ozonchick_new_version.Presentation.Screens.Responses.ResponsesViewModel
import com.example.ozonchick_new_version.Presentation.Screens.Resume.ResumeViewModel
import com.example.ozonchick_new_version.Presentation.Screens.Search.SearchViewModel
import com.example.ozonchick_new_version.Presentation.Screens.SignUp.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var AppModule = module {
    factory<Repository> { RepositoryImpl() }
    factory { AuthUseCase(get()) }
    viewModel {
        LogInViewModel(get())
    }
    factory {
        RegistrationCandidateUseCase(get())
    }
    factory { RegistrationEmployerUseCase(get()) }
    factory { RegistrationUseCase(get()) }
    viewModel {
        SignUpViewModel(get(), get(), get())
    }
    factory { GetNameUseCase(get()) }
    viewModel {
        ProfileViewModel(get())
//        (userId : Int) ->
//        ProfileViewModel(userId)
    }

    factory { GetResponsesIdVacanciesUseCase(get()) }
    factory { GetResponsesVacanciesUseCase(get()) }
    factory { RejectVacancyUseCase(get()) }
    viewModel {
        ResponsesViewModel(get(), get(), get())
    }
    factory { GetContactInformationUseCase(get()) }
    viewModel {
        (id : Int) -> ContactsViewModel(get(), id)
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
    factory { GetVacanciesUseCase(get()) }
    factory { AcceptVacancyUseCase(get()) }
    factory { SearchVacanciesUseCase(get()) }
    viewModel {
        ListVacanciesViewModel(get(), get(), get())
    }

    factory { GetEmployerVacanciesUseCase(get()) }
    factory { DeleteEmployerVacancyUseCase(get()) }
    viewModel {
        EmployerVacanciesViewModel(get(), get())
    }
    factory { UpdateEmployerVacancyUseCase(get()) }
    factory { GetVacancyAllInformationUseCase(get()) }
    viewModel {
        (description : VakansInformation) -> EmployerDetailsVacancyViewModel(get(), get(), description)
    }
    factory { GetUsersForEmployerUseCase(get()) }
    factory { AcceptCandidateUseCase(get()) }
    factory { RejectCandidateUseCase(get()) }
    viewModel {
        (vacancyId : Int) -> EmployerResponsesViewModel(vacancyId, get(), get(), get())
    }

    factory { AddVacancyUseCase(get()) }
    factory { GetOrganizationNameUseCase(get()) }
    viewModel {
        EmployerAddVacancyViewModel(get(), get())
    }

    factory { GetUserChangeableInformationUseCase(get()) }
    factory { UpdateUserInformationUseCase(get()) }
    viewModel {
        ChangeInformationViewModel(get(), get())
    }

    viewModel {
        SearchViewModel()
    }

    factory { UpdateResumeCandidateUseCase(get()) }
    factory { GetInformationResumeUseCase(get()) }
    viewModel {
        ResumeViewModel(get(), get())
    }

    factory { GetResumeCandidateUseCase(get()) }
    viewModel {
            (resumeId : Int) -> EmployerCandidateInformationViewModel(resumeId, get())
    }
}