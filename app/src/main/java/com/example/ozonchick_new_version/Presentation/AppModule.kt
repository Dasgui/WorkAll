package com.example.ozonchick_new_version.Presentation

import com.example.ozonchick_new_version.Data.network.KtorRepositories.AuthenticationKtorRepository
import com.example.ozonchick_new_version.Data.network.KtorRepositories.AuthenticationKtorRepositoryImpl
import com.example.ozonchick_new_version.Data.network.KtorRepositories.CandidateKtorRepository
import com.example.ozonchick_new_version.Data.network.KtorRepositories.CandidateKtorRepositoryImpl
import com.example.ozonchick_new_version.Data.network.KtorRepositories.EmployerKtorRepository
import com.example.ozonchick_new_version.Data.network.KtorRepositories.EmployerKtorRepositoryImpl
import com.example.ozonchick_new_version.Data.network.KtorRepositories.HomeKtorRepository
import com.example.ozonchick_new_version.Data.network.KtorRepositories.HomeKtorRepositoryImpl
import com.example.ozonchick_new_version.Data.network.KtorRepositories.VacancyKtorRepository
import com.example.ozonchick_new_version.Data.network.KtorRepositories.VacancyKtorRepositoryImpl
import com.example.ozonchick_new_version.Data.network.Repository
import com.example.ozonchick_new_version.Data.network.RepositoryImpl
import com.example.ozonchick_new_version.Domain.AcceptCandidateKtorUseCase
import com.example.ozonchick_new_version.Domain.AcceptCandidateUseCase
import com.example.ozonchick_new_version.Domain.AddResumeKtorUseCase
import com.example.ozonchick_new_version.Domain.AddVacancyResponseKtorUseCase
import com.example.ozonchick_new_version.Domain.AuthKtorUseCase
import com.example.ozonchick_new_version.Domain.AuthUseCase
import com.example.ozonchick_new_version.Domain.CreateVacancyKtorUseCase
import com.example.ozonchick_new_version.Domain.DeleteVacancyKtorUseCase
import com.example.ozonchick_new_version.Domain.FilterVacanciesKtorUseCase
import com.example.ozonchick_new_version.Domain.FindVacanciesKtorUseCase
import com.example.ozonchick_new_version.Domain.GetAllCandidateResponseKtorUseCase
import com.example.ozonchick_new_version.Domain.GetAllExperienceKtorUseCase
import com.example.ozonchick_new_version.Domain.GetAllResponsesUseCase
import com.example.ozonchick_new_version.Domain.GetCandidateResumeUseCase
import com.example.ozonchick_new_version.Domain.GetEmployerVacanciesKtorUseCase
import com.example.ozonchick_new_version.Domain.GetExperienceUseCase
import com.example.ozonchick_new_version.Domain.GetInformationResumeUseCase
import com.example.ozonchick_new_version.Domain.GetOrganizationNameUseCase
import com.example.ozonchick_new_version.Domain.GetOtklikAllStatusUseCase
import com.example.ozonchick_new_version.Domain.GetResumeCandidateUseCase
import com.example.ozonchick_new_version.Domain.GetResumeKtorUseCase
import com.example.ozonchick_new_version.Domain.GetUserInformationKtorUseCase
import com.example.ozonchick_new_version.Domain.GetUsersForEmployerUseCase
import com.example.ozonchick_new_version.Domain.GetVacanciesKtorUseCase
import com.example.ozonchick_new_version.Domain.GetVacanciesUseCase
import com.example.ozonchick_new_version.Domain.GetVacancyInformationKtorUseCase
import com.example.ozonchick_new_version.Domain.RegistrationKtorCandidateUseCase
import com.example.ozonchick_new_version.Domain.RegistrationKtorEmployerUseCase
import com.example.ozonchick_new_version.Domain.RejectCandidateKtorUseCase
import com.example.ozonchick_new_version.Domain.RejectCandidateResponseKtorUseCase
import com.example.ozonchick_new_version.Domain.RejectCandidateUseCase
import com.example.ozonchick_new_version.Domain.RejectVacancyUseCase
import com.example.ozonchick_new_version.Domain.SearchVacanciesKtorUseCase
import com.example.ozonchick_new_version.Domain.SearchVacanciesUseCase
import com.example.ozonchick_new_version.Domain.UpdateResumeCandidateUseCase
import com.example.ozonchick_new_version.Domain.UpdateResumeKtorUseCase
import com.example.ozonchick_new_version.Domain.UpdateUserInformationKtorUseCase
import com.example.ozonchick_new_version.Domain.UpdateVacancyInformationKtorUseCase
import com.example.ozonchick_new_version.Presentation.Screens.ChangeInformation.ChangeInformationViewModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerAddVacancy.EmployerAddVacancyViewModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerCandidateInformation.EmployerCandidateInformationViewModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerDetailsVacancy.EmployerDetailsVacancyViewModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerResponses.EmployerResponsesViewModel
import com.example.ozonchick_new_version.Presentation.Screens.EmployerVacancies.EmployerVacanciesViewModel
import com.example.ozonchick_new_version.Presentation.Screens.ListVacancies.ListVacanciesViewModel
import com.example.ozonchick_new_version.Presentation.ViewModels.ContactsViewModel
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
    factory<VacancyKtorRepository> { VacancyKtorRepositoryImpl() }
    factory<AuthenticationKtorRepository> { AuthenticationKtorRepositoryImpl() }
    factory<HomeKtorRepository> { HomeKtorRepositoryImpl() }
    factory<CandidateKtorRepository> { CandidateKtorRepositoryImpl() }
    factory<EmployerKtorRepository> { EmployerKtorRepositoryImpl() }

    factory { AuthUseCase(get()) }
    factory { AuthKtorUseCase(get()) }
    viewModel {
        LogInViewModel(get())
    }
    factory {
        RegistrationKtorCandidateUseCase(get())
    }
    factory { RegistrationKtorEmployerUseCase(get()) }
    viewModel {
        SignUpViewModel(get(), get())
    }
    factory { GetUserInformationKtorUseCase(get()) }
    viewModel {
        ProfileViewModel(get())
//        (userId : Int) ->
//        ProfileViewModel(userId)
    }

    factory { GetAllCandidateResponseKtorUseCase(get()) }
    factory { RejectCandidateResponseKtorUseCase(get()) }
    viewModel {
        ResponsesViewModel(get(), get())
    }
    factory { GetVacancyInformationKtorUseCase(get()) }
    viewModel {
        (id : Int) -> ContactsViewModel(get(), id)
    }

    viewModel {
        VakansCreate_ViewModel()
    }
    factory { GetVacanciesUseCase(get()) }
    factory { AddVacancyResponseKtorUseCase(get()) }
    factory { SearchVacanciesKtorUseCase(get()) }
    factory { FilterVacanciesKtorUseCase(get()) }
    factory { FindVacanciesKtorUseCase(get()) }

    factory { GetVacanciesKtorUseCase(get()) }
    viewModel {
        ListVacanciesViewModel(get(), get(), get(), get(), get())
    }

    factory { GetEmployerVacanciesKtorUseCase(get()) }
    factory { DeleteVacancyKtorUseCase(get()) }
    viewModel {
        EmployerVacanciesViewModel(get(), get())
    }


    factory { GetVacancyInformationKtorUseCase(get ()) }
    factory { GetAllExperienceKtorUseCase(get()) }
    factory { UpdateVacancyInformationKtorUseCase(get()) }
    viewModel {
        (id : Int) -> EmployerDetailsVacancyViewModel(id, get(), get(), get())
    }


    factory { GetAllResponsesUseCase(get()) }
    factory { GetOtklikAllStatusUseCase(get()) }

    factory { AcceptCandidateKtorUseCase(get()) }
    factory { RejectCandidateKtorUseCase(get()) }
    viewModel {
        (vacancyId : Int) -> EmployerResponsesViewModel(vacancyId, get(), get(), get(), get())
    }

    factory { CreateVacancyKtorUseCase(get()) }
    factory { GetOrganizationNameUseCase(get()) }
    factory { GetAllExperienceKtorUseCase(get()) }
    viewModel {
        EmployerAddVacancyViewModel(get(), get(), get())
    }

    factory { UpdateUserInformationKtorUseCase(get()) }
    viewModel {
        ChangeInformationViewModel(get(), get())
    }

    factory { GetAllExperienceKtorUseCase(get()) }
    viewModel {
        SearchViewModel(get())
    }

    factory { GetResumeKtorUseCase(get()) }
    factory { AddResumeKtorUseCase(get()) }
    factory { UpdateResumeKtorUseCase(get()) }
    factory { GetAllExperienceKtorUseCase(get()) }
    viewModel {
        ResumeViewModel(get(), get(), get(), get())
    }

    factory { GetCandidateResumeUseCase(get()) }
    viewModel {
            (resumeId : Int) -> EmployerCandidateInformationViewModel(resumeId, get())
    }
}