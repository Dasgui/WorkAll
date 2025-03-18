package com.example.ozonchick_new_version.Data.network

import com.example.ozonchick_new_version.Data.model.Vakans_information

interface PostService {
    suspend fun getAllPosts():List<Vakans_information>
}