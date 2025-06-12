package com.example.ozonchick_new_version.Data.network

import com.example.ozonchick_new_version.Common.localUserInformation
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.path

import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds


val supabase = createSupabaseClient(
        supabaseUrl = "https://wcbzgbxitneiesusyuaz.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndjYnpnYnhpdG5laWVzdXN5dWF6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTIzNzg3MDMsImV4cCI6MjAyNzk1NDcwM30.cR9vcGfk8I5TRr3biQcMEbxd0TY1Vxkcr-TxXM4PboE"
    ) {
        install(Postgrest)
        install(Realtime)
        {
            reconnectDelay = 3.seconds
        }
    }

val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        json( Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }
    defaultRequest {
        url {
            url("https://isp-workall.online/")
            path("api/v1/")
        }
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
//        header(HttpHeaders.ContentType, ContentType.Application.Json)
//        header("accept", "application/json")
    }
    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.ALL
    }
/*    install(Auth) {
        bearer {
            loadTokens {
                BearerTokens("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOjE5LCJlbWFpbCI6ImVtcG1vYkBtYWlsLnJ1Iiwicm9sZSI6ImVtcGxveWVlIiwiZXhwIjoxNzQ5MzAzMjYzLCJpYXQiOjE3NDkyMTk5NTh9.cS-y2qyz8hCcV6eO6QvrlpkSs1Q1X9qOnJUm9tF9OMw", "")
            }
        }
    }*/
}

val json = Json { ignoreUnknownKeys = true }



