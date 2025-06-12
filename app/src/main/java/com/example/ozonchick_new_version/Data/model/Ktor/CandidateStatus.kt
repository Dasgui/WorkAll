package com.example.ozonchick_new_version.Data.model.Ktor


sealed class CandidateStatus {
    object Accept : CandidateStatus()
    object None : CandidateStatus()
    object Reject : CandidateStatus()
}

var candidateStatuses = mapOf(3 to "На рассмотрении", 7 to "Приглашение", 8 to "Отказ")
