package com.example.ozonchick_new_version.Data.network

import com.example.ozonchick_new_version.Data.model.Candidate.CandidateFilterInformation
import com.example.ozonchick_new_version.Data.model.Employer.EmployerFilterInformation
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

suspend fun findUser (email : String, phoneNumber : String) : Deferred<Boolean> {
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