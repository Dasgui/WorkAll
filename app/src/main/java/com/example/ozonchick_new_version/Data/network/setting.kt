package com.example.ozonchick_new_version.Data.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
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




