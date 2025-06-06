package com.example.ozonchick_new_version.Data.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
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
        url("https://isp-workall.online/api/v1/")
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
    install(Logging)
}

val json = Json { ignoreUnknownKeys = true }



