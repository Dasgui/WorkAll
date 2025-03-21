package com.example.ozonchick_new_version.Domain

import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Data.network.Repository

class GetNameUseCase(private val repository: Repository) {
    suspend operator fun invoke(userId : UserId) : CustomResult<String> {
        return repository.getName(userId)
    }
}