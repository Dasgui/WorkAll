package com.example.ozonchick_new_version.Domain


import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.SigLogInModels.User
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