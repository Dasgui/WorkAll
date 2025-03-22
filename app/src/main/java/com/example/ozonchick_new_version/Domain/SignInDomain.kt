package com.example.ozonchick_new_version.Domain


import android.util.Log
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.Authentication.AllUserInformation
import com.example.ozonchick_new_version.Data.model.Authentication.AllUserInsertInformation
import com.example.ozonchick_new_version.Data.network.Repository

class AuthUseCase(private val repository: Repository) {
    suspend operator fun invoke (email: String, password : String) : CustomResult<AllUserInformation> {
        var result = repository.auth(email, password)
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

class RegistrationCandidateUseCase(private val repository: Repository) {
    suspend operator fun invoke (name: String, email: String, password : String, phoneNumber: String) : CustomResult<List<AllUserInsertInformation>> {
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
    suspend operator fun invoke (name: String, email: String,
                                 password : String, phoneNumber: String, inn : String, organizationName : String) : CustomResult<List<AllUserInsertInformation>> {
        var result = repository.registrationEmployer(name, password, phoneNumber, email, inn, organizationName)
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