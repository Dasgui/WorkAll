package com.example.ozonchick_new_version.Domain


import android.util.Log
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.AllUserInsertInformation
import com.example.ozonchick_new_version.Data.model.SigLogInModels.User
import com.example.ozonchick_new_version.Data.model.Candidate.UserInsertInformation
import com.example.ozonchick_new_version.Data.model.Employer.EmployerInsertInformation
import com.example.ozonchick_new_version.Data.network.Repository

class AuthUseCase(private val repository: Repository) {
    suspend operator fun invoke (email: String, password : String) : CustomResult<User> {
        var result = repository.Auth(email, password)
        result = when (val curResult = result) {
            is CustomResult.Failure -> {
                CustomResult.Failure(curResult.code, curResult.message)
            }
            is CustomResult.Success -> {
                if (curResult.data.email.isNotEmpty()) {
                    CustomResult.Success(curResult.data)
                } else {
                    CustomResult.Failure(0, "Пользователя не существует")
                }
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
                                 password : String, phoneNumber: String, inn : String) : CustomResult<List<AllUserInsertInformation>> {
        var result = repository.registrationEmployer(name, password, phoneNumber, email, inn)
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