package com.example.ozonchick_new_version.Domain

import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Data.model.Vakans_information
import com.example.ozonchick_new_version.Data.network.Repository

class GetVacanciesUseCase (private val repository: Repository) {
    suspend operator fun invoke () : CustomResult<List<Vakans_information>> {
        var result = repository.getVacancies()
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

class GetContactInformationUseCase (private val repository: Repository) {
    suspend operator fun invoke (id : Int) : CustomResult<List<Vakans_contacts>> {
        var result = repository.getContactInformation(id)
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