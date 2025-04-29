package com.example.ozonchick_new_version.Data.network

import android.util.Log
import com.example.ozonchick_new_version.Common.CustomResult
import com.example.ozonchick_new_version.Common.UserType
import com.example.ozonchick_new_version.Common.model.UserId
import com.example.ozonchick_new_version.Common.publicUserId
import com.example.ozonchick_new_version.Data.model.Authentication.AllUserInformation
import com.example.ozonchick_new_version.Data.model.Authentication.AllUserInsertInformation
import com.example.ozonchick_new_version.Data.model.Authentication.Candidate.Candidate
import com.example.ozonchick_new_version.Data.model.Authentication.Candidate.UserInsertInformation
import com.example.ozonchick_new_version.Data.model.Authentication.Employer.Employer
import com.example.ozonchick_new_version.Data.model.Authentication.Employer.EmployerInsertInformation
import com.example.ozonchick_new_version.Data.model.Authentication.UserChangeableInformation
import com.example.ozonchick_new_version.Data.model.Resume.EducationGet
import com.example.ozonchick_new_version.Data.model.Resume.ExperienceGet
import com.example.ozonchick_new_version.Data.model.ListOfVacancies.StatesOfGetVacancies
import com.example.ozonchick_new_version.Data.model.Resume.ResumeDto
import com.example.ozonchick_new_version.Data.model.Resume.ResumeGet
import com.example.ozonchick_new_version.Data.model.Resume.ResumePost
import com.example.ozonchick_new_version.Data.model.VacancyDescriptionInsertModel
import com.example.ozonchick_new_version.Data.model.VacancyInsertAccept
import com.example.ozonchick_new_version.Data.model.VacancyUpdateInformation
import com.example.ozonchick_new_version.Data.model.Vakans_contacts
import com.example.ozonchick_new_version.Data.model.VakansInformation
import com.example.ozonchick_new_version.Data.model.VakansOtklik
import com.example.ozonchick_new_version.Data.model.VacancyResponse
import com.example.ozonchick_new_version.Data.strings.AppErrors
import com.example.ozonchick_new_version.Presentation.Models.ChangeVacancyUIModel
import com.example.ozonchick_new_version.Presentation.Models.ExclusionOptions
import io.github.jan.supabase.exceptions.HttpRequestException

import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.paperdb.Paper

interface Repository {
    suspend fun auth(
        email: String,
        password: String,
        rememberUser: Boolean
    ): CustomResult<AllUserInformation>

    suspend fun getVacancies(): CustomResult<List<VakansInformation>>
    suspend fun getResponsesIdVacancies(userId: UserId): CustomResult<List<VacancyResponse>>
    suspend fun getResponsesVacancies(idList: List<Int>): CustomResult<List<VakansInformation>>

    suspend fun getContactInformation(id: Int): CustomResult<List<Vakans_contacts>>
    suspend fun registrationCandidate(
        name: String,
        password: String,
        phoneNumber: String,
        email: String
    ): CustomResult<List<AllUserInsertInformation>>

    suspend fun registrationEmployer(
        name: String,
        password: String,
        phoneNumber: String,
        email: String,
        inn: String,
        organizationName: String
    ): CustomResult<List<AllUserInsertInformation>>

    suspend fun getName(userId: UserId): CustomResult<String>
    suspend fun getOrganizationName(userId: UserId): CustomResult<String>

    suspend fun getEmployerVacancies(userId: UserId): CustomResult<List<VakansInformation>>
    suspend fun deleteVacancies(vacancyId: Int): CustomResult<Boolean>
    suspend fun updateVacancy(
        vacancyId: Int,
        information: ChangeVacancyUIModel
    ): CustomResult<Boolean>

    suspend fun getUsersForEmployer(vacancyId: Int): CustomResult<List<Candidate>>
    suspend fun acceptCandidate(candidateId: Int): CustomResult<Boolean>
    suspend fun rejectCandidate(candidateId: Int): CustomResult<Boolean>
    suspend fun addVacancy(
        vacancy: VacancyUpdateInformation,
        description: VacancyDescriptionInsertModel
    ): CustomResult<Boolean>

    suspend fun acceptVacancy(candidateId: Int, vacancyId: Int): CustomResult<Boolean>
    suspend fun rejectVacancy(candidateId: Int, vacancyId: Int): CustomResult<Boolean>

    suspend fun updateUserInformation(
        id: Int,
        information: UserChangeableInformation
    ): CustomResult<Boolean>

    suspend fun getUserChangeableInformation(id: Int): CustomResult<UserChangeableInformation>

    suspend fun searchVacancies (getStates : StatesOfGetVacancies.Filter) : CustomResult<List<VakansInformation>>

    suspend fun updateCandidateResume (resume : ResumePost) : CustomResult<Boolean>
    suspend fun getEducations () : CustomResult<List<EducationGet>>
    suspend fun getExperiences () : CustomResult<List<ExperienceGet>>

    suspend fun getResume (id : Int) : CustomResult<ResumeGet>
    suspend fun getEducation (id : Int) : CustomResult<EducationGet>
    suspend fun getExperience (id : Int) : CustomResult<ExperienceGet>
}

class RepositoryImpl : Repository {
    override suspend fun auth(
        email: String,
        password: String,
        rememberUser: Boolean
    ): CustomResult<AllUserInformation> {
        return try {
            val candidate = supabase.from("user_information").select() {
                filter {
                    AllUserInformation.Candidate::email eq email
                    AllUserInformation.Candidate::password eq password
                }
            }.decodeList<AllUserInformation.Candidate>()
            Log.d("Запоминание юзера", rememberUser.toString())
            if (rememberUser == true) {
                Paper.book().write("UserId", publicUserId)
            }
            if (candidate.isEmpty()) {
                val employer = supabase.from("employers_information").select() {
                    filter {
                        AllUserInformation.Employer::email eq email
                        AllUserInformation.Employer::password eq password
                    }
                }.decodeList<AllUserInformation.Employer>()
                CustomResult.Success(employer[0])
            } else {
                CustomResult.Success(candidate[0])
            }
        } catch (ex: HttpRequestException) {
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        } catch (ex: IndexOutOfBoundsException) {
            CustomResult.Failure(
                ExclusionOptions.UserExistsError.id,
                ExclusionOptions.UserExistsError.message
            )
        } catch (ex: Exception) {
            Log.d("Ошибка при входе", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.UnknownError.id,
                ExclusionOptions.UnknownError.message
            )
        }
    }

    override suspend fun getVacancies(): CustomResult<List<VakansInformation>> {
        return try {
            val result = supabase.from("vakans").select().decodeList<VakansInformation>()
            CustomResult.Success(result)
        } catch (ex: Exception) {
            Log.d("Получение всех вакансий", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun getResponsesIdVacancies(userId: UserId): CustomResult<List<VacancyResponse>> {
        return try {
            val result = supabase.from("vakans_user_otklik").select() {
                filter {
                    VacancyResponse::user_long_id isIn listOf(userId.id)
                }
            }.decodeList<VacancyResponse>()
            CustomResult.Success(result)
        } catch (ex: Exception) {
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun getResponsesVacancies(idList: List<Int>): CustomResult<List<VakansInformation>> {
        return try {
            val result = supabase.from("vakans").select() {
                filter {
                    VakansInformation::vakans_id isIn idList
                }
            }.decodeList<VakansInformation>()
            CustomResult.Success(result)
        } catch (ex: Exception) {
            Log.d("Получение откликов вакансий", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun getContactInformation(id: Int): CustomResult<List<Vakans_contacts>> {
        return try {
            val result = supabase.from("vakans_contacts")
                .select()
                {
                    filter {
                        Vakans_contacts::vakans_id eq id
                    }
                }.decodeList<Vakans_contacts>()
            CustomResult.Success(result)
        } catch (ex: Exception) {
            Log.d("Получение дитальной информации", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun registrationCandidate(
        name: String,
        password: String,
        phoneNumber: String,
        email: String,
    ): CustomResult<List<AllUserInsertInformation>> {
        return try {
            val user = UserInsertInformation(
                name = name,
                phoneNumber = phoneNumber,
                email = email,
                password = password
            )
            if (isUserExist(email, phoneNumber).await()) {
                val result = supabase.from("user_information").insert(user) {
                    select()
                }.decodeList<AllUserInsertInformation.UserInsertInformation>()
                CustomResult.Success(result)
            } else {
                CustomResult.Failure(
                    ExclusionOptions.UserIsAlreadyExists.id,
                    ExclusionOptions.UserIsAlreadyExists.message
                )
            }
        } catch (ex: HttpRequestException) {
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        } catch (ex: Exception) {
            Log.d("Регистрация", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.UnknownError.id,
                ExclusionOptions.UnknownError.message
            )
        }
    }

    override suspend fun registrationEmployer(
        name: String,
        password: String,
        phoneNumber: String,
        email: String,
        inn: String,
        organizationName: String
    ): CustomResult<List<AllUserInsertInformation>> {
        return try {
            val user = EmployerInsertInformation(
                name = name,
                phoneNumber = phoneNumber,
                email = email,
                password = password,
                INN = inn,
                companyName = organizationName
            )
            if (isUserExist(email, phoneNumber).await()) {
                val result = supabase.from("employers_information").insert(user) {
                    select()
                }.decodeList<AllUserInsertInformation.EmployerInsertInformation>()
                CustomResult.Success(result)
            } else {
                CustomResult.Failure(
                    ExclusionOptions.UserIsAlreadyExists.id,
                    ExclusionOptions.UserIsAlreadyExists.message
                )
            }
        } catch (ex: HttpRequestException) {
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        } catch (ex: Exception) {
            Log.d("Регистрация", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.UnknownError.id,
                ExclusionOptions.UnknownError.message
            )
        }
    }

    override suspend fun getName(userId: UserId): CustomResult<String> {
        return try {
            if (userId.type is UserType.Employer) {
                val result = supabase.from("employers_information")
                    .select()
                    {
                        filter {
                            Employer::employer_id eq userId.id
                        }
                    }.decodeList<Employer>()
                Log.d("Имя работодателя", result[0].name)
                CustomResult.Success(result[0].name)
            } else {
                val result = supabase.from("user_information")
                    .select()
                    {
                        filter {
                            Candidate::user_id eq userId.id
                        }
                    }.decodeList<Candidate>()
                Log.d("Имя соискателя", result[0].name)
                CustomResult.Success(result[0].name)
            }
        } catch (ex: Exception) {
            Log.d("Ошибка получения имени", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun getOrganizationName(userId: UserId): CustomResult<String> {
        return try {
            val result = supabase.from("employers_information")
                .select()
                {
                    filter {
                        Employer::employer_id eq userId.id
                    }
                }.decodeList<Employer>()
            Log.d("Имя компании", result[0].companyName)
            CustomResult.Success(result[0].companyName)
        } catch (ex: Exception) {
            Log.d("Ошибка получения имени", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun getEmployerVacancies(userId: UserId): CustomResult<List<VakansInformation>> {
        return try {
            val result = supabase.from("vakans")
                .select()
                {
                    filter {
                        VakansInformation::employer_id eq userId.id
                    }
                }.decodeList<VakansInformation>()
            Log.d("Вакансии работодателя", result.toString())
            CustomResult.Success(result)
        } catch (ex: Exception) {
            Log.d("Ошибка получения вакансий работодателя", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun deleteVacancies(vacancyId: Int): CustomResult<Boolean> {
        return try {
            supabase.from("vakans_user_otklik")
                .delete()
                {
                    filter {
                        VakansOtklik::vakans_id eq vacancyId
                    }
                }
            supabase.from("vakans").delete() {
                filter {
                    VakansInformation::vakans_id eq vacancyId
                }
            }
            CustomResult.Success(true)
        } catch (ex: Exception) {
            Log.d("Ошибка получения при удалении", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun updateVacancy(
        vacancyId: Int,
        information: ChangeVacancyUIModel
    ): CustomResult<Boolean> {
        return try {
            supabase.from("vakans_contacts").update({
                Vakans_contacts::vakans_number setTo information.description.vakans_number
                set("vakans_aboutWork", information.description.vakans_aboutWork)
                Vakans_contacts::vakans_mail setTo information.description.vakans_mail
            }) {
                filter {
                    Vakans_contacts::vakans_id eq vacancyId
                }
            }
            supabase.from("vakans").update(
                {
                    VakansInformation::vakans_mail setTo information.mainInformation.vakans_mail
                    VakansInformation::vakans_price setTo information.mainInformation.vakans_price
                    VakansInformation::vakans_location setTo information.mainInformation.vakans_location
                    VakansInformation::vakans_experience setTo information.mainInformation.vakans_experience
                    VakansInformation::vakans_name setTo information.mainInformation.vakans_name
                }
            ) {
                filter {
                    VakansInformation::vakans_id eq vacancyId
                }
            }
            CustomResult.Success(true)
        } catch (ex: Exception) {
            Log.d("Ошибка при изменении", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun getUsersForEmployer(vacancyId: Int): CustomResult<List<Candidate>> {
        return try {
            val users = supabase.from("vakans_user_otklik")
                .select()
                {
                    filter {
                        eq("vakans_id", vacancyId)
                        eq("otvet", "На рассмотрении")
                    }
                }.decodeList<VakansOtklik>()
            val usersInformation = supabase.from("user_information")
                .select()
                {
                    filter {
                        Candidate::user_id isIn users.map { it.user_long_id }
                    }
                }.decodeList<Candidate>()
            CustomResult.Success(usersInformation)
        } catch (ex: Exception) {
            Log.d("Ошибка получения пользователей", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun acceptCandidate(candidateId: Int): CustomResult<Boolean> {
        return try {
            supabase.from("vakans_user_otklik")
                .update(
                    {
                        set("otvet", "Вы приняты")
                    }
                )
                {
                    filter {
                        eq("user_long_id", candidateId)
                    }
                }
            CustomResult.Success(true)
        } catch (ex: Exception) {
            Log.d("Ошибка принятия пользователя", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun rejectCandidate(candidateId: Int): CustomResult<Boolean> {
        return try {
            supabase.from("vakans_user_otklik")
                .update(
                    {
                        set("otvet", "Вам отказано")
                    }
                )
                {
                    filter {
                        eq("user_long_id", candidateId)
                    }
                }
            CustomResult.Success(true)
        } catch (ex: Exception) {
            Log.d("Ошибка отказа пользователя", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun addVacancy(
        vacancy: VacancyUpdateInformation,
        description: VacancyDescriptionInsertModel
    ): CustomResult<Boolean> {
        return try {
            val vacancy = supabase.from("vakans").insert(vacancy) { select() }
                .decodeSingle<VakansInformation>()
            description.vakans_id = vacancy.vakans_id
            supabase.from("vakans_contacts").insert(description)
            CustomResult.Success(true)
        } catch (ex: Exception) {
            Log.d("Ошибка создания вакансии", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun acceptVacancy(
        candidateId: Int,
        vacancyId: Int
    ): CustomResult<Boolean> {
        return try {
            val response = supabase.from("vakans_user_otklik").select() {
                filter {
                    eq("vakans_id", vacancyId)
                    eq("user_long_id", candidateId)
                }
            }.decodeList<VacancyInsertAccept>()
            if (response.isEmpty()) {
                val vacancy = supabase.from("vakans_user_otklik")
                    .insert(VacancyInsertAccept(vacancyId, candidateId))
                CustomResult.Success(true)
            } else {
                CustomResult.Failure(
                    ExclusionOptions.VacancyIsAlreadyExists.id,
                    ExclusionOptions.VacancyIsAlreadyExists.message
                )
            }

        } catch (ex: Exception) {
            Log.d("Ошибка принятия пользователя", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun rejectVacancy(
        candidateId: Int,
        vacancyId: Int
    ): CustomResult<Boolean> {
        return try {
            supabase.from("vakans_user_otklik").delete() {
                filter {
                    eq("vakans_id", vacancyId)
                    eq("user_long_id", candidateId)
                }
            }
            CustomResult.Success(true)

        } catch (ex: Exception) {
            Log.d("Ошибка удаления отклика на вакансию", ex.toString())
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                ExclusionOptions.NetworkError.message
            )
        }
    }

    override suspend fun updateUserInformation(
        id: Int,
        information: UserChangeableInformation
    ): CustomResult<Boolean> {
        return try {
            var userType = getUserTypeById(id).await()
            val isUpdated = when (userType) {
                is UserType.Candidate -> (supabase.from("user_information").update({
                    set("name", information.name)
                    set("password", information.password)
                    set("phoneNumber", information.phoneNumber)
                    set("email", information.email)
                }) {
                    filter {
                        eq("user_id", id)
                    }
                }.countOrNull() ?: 0) > 0

                is UserType.Employer -> {
                    (supabase.from("employers_information").update({
                        set("name", information.name)
                        set("password", information.password)
                        set("phoneNumber", information.phoneNumber)
                        set("email", information.email)
                    }) {
                        filter {
                            eq("employer_id", id)
                        }
                    }.countOrNull() ?: 0) > 0
                }

                UserType.None -> false
            }
            CustomResult.Success(isUpdated)
        } catch (ex: Exception) {
            CustomResult.Failure(
                ExclusionOptions.NetworkError.id,
                "Не удалось обновить информацию \n ${ex.message}"
            )
        }
    }

    override suspend fun getUserChangeableInformation(id: Int): CustomResult<UserChangeableInformation> {
        return try {
            var userType = getUserTypeById(id).await()
            when (userType) {
                UserType.Candidate -> {
                    val responseCandidate = supabase.from("user_information")
                        .select(Columns.list("name", "password", "phoneNumber", "email"))
                        {
                            filter {
                                eq("user_id", id)
                            }
                        }.decodeSingle<UserChangeableInformation>()
                    CustomResult.Success(responseCandidate)
                }

                UserType.Employer -> {
                    val responseEmployer = supabase.from("employers_information")
                        .select(Columns.list("name", "password", "phoneNumber", "email"))
                        {
                            filter {
                                eq("employer_id", id)
                            }
                        }.decodeSingle<UserChangeableInformation>()
                    CustomResult.Success(responseEmployer)
                }

                UserType.None -> CustomResult.Failure(1, "неизвестная ошибка")
            }
        } catch (ex: Exception) {
            CustomResult.Failure(1, "Проверьте своё соиденение с интернетом \n ${ex.message}")
        }
    }

    override suspend fun searchVacancies(getStates: StatesOfGetVacancies.Filter): CustomResult<List<VakansInformation>> {
        return try {
            val response = supabase.from("vakans").select {
                filter {
                    ilike("vakans_name", "%${getStates.search}%")
                    if (getStates.filterSalary.isNotEmpty() && getStates.filterSalary.isNotEmpty()) {
                        gte("vakans_price", getStates.filterSalary)
                    }
                    if (getStates.filterExperience.isNotBlank()) {
                        eq("vakans_experience", getStates.filterExperience)
                    }
                }
            }.decodeList<VakansInformation>()
            CustomResult.Success(response)
        } catch (ex : Exception) {
            CustomResult.Failure(1, AppErrors.INTERNET.description)
        }
    }

    override suspend fun updateCandidateResume(resume: ResumePost): CustomResult<Boolean> {
        return try {
            val user = supabase.from("user_information").select() {
                filter {
                    eq("user_id", publicUserId.id)
                }
            }.decodeSingle<Candidate>()
            val resume = if (user.resume_id != 0) {
                supabase.from("Resume").update({
                    set("experience_id", resume.experience_id)
                    set("description", resume.description)
                    set("professionName", resume.professionName)
                    set("education", resume.education)
                }) {
                    select()
                    filter {
                        eq("id", user.resume_id)
                    }
                }.decodeSingleOrNull<ResumeGet>()
            } else {
                 supabase.from("Resume").insert(
                    resume
                ) {
                    select()
                }.decodeSingleOrNull<ResumeGet>()
            }

            if (resume == null) {
                CustomResult.Failure(0, AppErrors.UPDATE_INFORMATION.description)
            }

            supabase.from("user_information").update(
                {
                    set("resume_id", resume?.id)
                }
            ) {
                filter {
                    eq("user_id", publicUserId.id)
                }
            }
            CustomResult.Success(true)
        } catch (ex : Exception) {
            Log.d("Ошибка при создании/изменении резюме", ex.toString())
            CustomResult.Failure(0, AppErrors.INTERNET.description)

        }
    }

    override suspend fun getEducations(): CustomResult<List<EducationGet>> {
        return try {
            val education = supabase.from("Education").select().decodeList<EducationGet>()
            CustomResult.Success(education)
        } catch (ex : Exception) {
            CustomResult.Failure(0, AppErrors.INTERNET.description)
        }
    }

    override suspend fun getExperiences(): CustomResult<List<ExperienceGet>> {
        return try {
            val education = supabase.from("Experience_status").select().decodeList<ExperienceGet>()
            CustomResult.Success(education)
        } catch (ex : Exception) {
            CustomResult.Failure(0, AppErrors.INTERNET.description)
        }
    }

    override suspend fun getResume(id : Int): CustomResult<ResumeGet> {
        return try {
            val resume = supabase.from("Resume").select() {
                filter {
                    eq("id", id)
                }
            }.decodeSingle<ResumeGet>()
            CustomResult.Success(resume)
        } catch (ex : Exception) {
            CustomResult.Failure(0, AppErrors.INTERNET.description)
        }
    }

    override suspend fun getEducation(id: Int): CustomResult<EducationGet> {
        return try {
            val education = supabase.from("Education").select() {
                filter {
                    eq("id", id)
                }
            }.decodeSingle<EducationGet>()
            CustomResult.Success(education)
        } catch (ex : Exception) {
            CustomResult.Failure(0, AppErrors.INTERNET.description)
        }
    }

    override suspend fun getExperience(id: Int): CustomResult<ExperienceGet> {
        return try {
            val experience = supabase.from("Experience_status").select() {
                filter {
                    eq("id", id)
                }
            }.decodeSingle<ExperienceGet>()
            CustomResult.Success(experience)
        } catch (ex : Exception) {
            CustomResult.Failure(0, AppErrors.INTERNET.description)
        }
    }
}