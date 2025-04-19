package com.example.ozonchick_new_version.Domain

import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Data.model.Authentication.UserChangeableInformation
import com.example.ozonchick_new_version.Data.network.Repository

class GetNameUseCase(private val repository: Repository) {
    suspend operator fun invoke(userId: UserId): CustomResult<String> {
        return repository.getName(userId)
    }
}

class UpdateUserInformationUseCase(private val repository: Repository) {
    suspend operator fun invoke(id: Int, information: UserChangeableInformation) : CustomResult<Boolean> {
        return repository.updateUserInformation(id, information)
    }
}

class GetUserChangeableInformationUseCase (private val repository: Repository) {
    suspend operator fun invoke (id : Int) : CustomResult<UserChangeableInformation> {
        return repository.getUserChangeableInformation(id)
    }
}