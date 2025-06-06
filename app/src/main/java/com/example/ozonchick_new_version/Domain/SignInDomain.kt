package com.example.ozonchick_new_version.Domain


import android.util.Log
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.Authentication.AllUserInformation
import com.example.ozonchick_new_version.Data.model.Authentication.AllUserInsertInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.AllTypeUserInformation
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.CandidateInfo
import com.example.ozonchick_new_version.Data.model.Ktor.Authetication.EmployerInfo
import com.example.ozonchick_new_version.Data.network.KtorRepositories.KtorRepository
import com.example.ozonchick_new_version.Data.network.Repository
import com.example.ozonchick_new_version.Data.network.RepositoryImpl

class AuthUseCase(private val repository: Repository) {
    suspend operator fun invoke(
        email: String,
        password: String,
        rememberUser: Boolean
    ): CustomResult<AllUserInformation> {
        var result = repository.auth(email, password, rememberUser)
        result = when (val curResult = result) {
            is CustomResult.Failure -> {
                CustomResult.Failure(curResult.code, curResult.message)
            }

            is CustomResult.Success -> {
                CustomResult.Success(curResult.data)
            }
        }
        return result
    }
}

class AuthKtorUseCase(private val repository: KtorRepository) {
    suspend operator fun invoke(
        email: String,
        password: String,
        rememberUser: Boolean
    ): CustomResult<AllTypeUserInformation> {
        var result = repository.auth(email, password, rememberUser)
        result = when (val curResult = result) {
            is CustomResult.Failure -> {
                CustomResult.Failure(curResult.code, curResult.message)
            }

            is CustomResult.Success -> {
                CustomResult.Success(curResult.data)
            }
        }
        return result
    }
}

class RegistrationKtorCandidateUseCase(private val repository: KtorRepository) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        phoneNumber: String
    ): CustomResult<CandidateInfo> {
        var result = repository.registrationCandidate(name, password, phoneNumber, email)
        result = when (val curResult = result) {
            is CustomResult.Failure -> {
                Log.d("Регистрация", curResult.message)
                CustomResult.Failure(curResult.code, curResult.message)
            }

            is CustomResult.Success -> {
                CustomResult.Success(curResult.data)
            }
        }
        return result
    }
}

class RegistrationKtorEmployerUseCase(private val repository: KtorRepository) {
    suspend operator fun invoke(
        name: String, email: String,
        password: String, phoneNumber: String, inn: String, organizationName: String
    ): CustomResult<EmployerInfo> {
        var result = repository.registrationEmployer(name, password, phoneNumber, email, inn, organizationName)
        result = when (val curResult = result) {
            is CustomResult.Failure -> {
                Log.d("Регистрация", curResult.message)
                CustomResult.Failure(curResult.code, curResult.message)
            }

            is CustomResult.Success -> {
                CustomResult.Success(curResult.data)
            }
        }
        return result
    }
}


class RegistrationUseCase(private val repository: Repository) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        phoneNumber: String
    ): CustomResult<List<AllUserInsertInformation>> {
        return repository.registrationCandidate(name, password, phoneNumber, email)
    }

    suspend operator fun invoke(
        name: String, email: String,
        password: String, phoneNumber: String, inn: String, organizationName: String
    ): CustomResult<List<AllUserInsertInformation>> {
        return repository.registrationEmployer(
            name,
            password,
            phoneNumber,
            email,
            inn,
            organizationName
        )
    }
}

class RegistrationCandidateUseCase(private val repository: Repository) {
    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        phoneNumber: String
    ): CustomResult<List<AllUserInsertInformation>> {
        var result = repository.registrationCandidate(name, password, phoneNumber, email)
        result = when (val curResult = result) {
            is CustomResult.Failure -> {
                Log.d("Регистрация", curResult.message)
                CustomResult.Failure(curResult.code, curResult.message)
            }

            is CustomResult.Success -> {
                CustomResult.Success(curResult.data)
            }
        }
        return result
    }
}

class RegistrationEmployerUseCase(private val repository: Repository) {
    suspend operator fun invoke(
        name: String, email: String,
        password: String, phoneNumber: String, inn: String, organizationName: String
    ): CustomResult<List<AllUserInsertInformation>> {
        var result = repository.registrationEmployer(
            name,
            password,
            phoneNumber,
            email,
            inn,
            organizationName
        )
        result = when (val curResult = result) {
            is CustomResult.Failure -> {
                CustomResult.Failure(curResult.code, curResult.message)

            }

            is CustomResult.Success -> {
                CustomResult.Success(curResult.data)
            }
        }
        return result
    }
}