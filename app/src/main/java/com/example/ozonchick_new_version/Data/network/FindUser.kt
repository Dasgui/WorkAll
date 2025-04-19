package com.example.ozonchick_new_version.Data.network

import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Data.model.Authentication.Candidate.Candidate
import com.example.ozonchick_new_version.Data.model.Authentication.Candidate.CandidateFilterInformation
import com.example.ozonchick_new_version.Data.model.Authentication.Employer.Employer
import com.example.ozonchick_new_version.Data.model.Authentication.Employer.EmployerFilterInformation
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

suspend fun isUserExist (email : String, phoneNumber : String) : Deferred<Boolean> {
    return coroutineScope {
        async {
            var findCandidate = supabase.from("user_information").select(columns = Columns.list("phoneNumber, email"))
            {
                filter {
                    or {
                        eq("phoneNumber", phoneNumber)
                        eq("email", email)
                    }
                }
            }.decodeList<CandidateFilterInformation>()
            val findEmployer = supabase.from("employers_information").select(columns = Columns.list("phoneNumber, email, INN"))
            {
                filter {
                    or {
                        eq("phoneNumber", phoneNumber)
                        eq("email", email)
                    }
                }
            }.decodeList<EmployerFilterInformation>()
            findCandidate.isEmpty() && findEmployer.isEmpty()
        }
    }
}

suspend fun getUserTypeById (id : Int) : Deferred<UserType> {
    return coroutineScope {
        async(Dispatchers.IO) {
            val candidate = supabase.from("user_information").select {
                filter {
                    eq("user_id", id)
                }
            }.decodeList<Candidate>()

            val employer = supabase.from("employers_information").select {
                filter {
                    eq("employer_id", id)
                }
            }.decodeList<Employer>()

            when {
                candidate.isNotEmpty() -> UserType.Candidate
                employer.isNotEmpty() -> UserType.Employer
                else -> UserType.None
            }
        }
    }
}
