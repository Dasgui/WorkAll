package com.example.ozonchick_new_version.Data_Layer.network

import com.example.ozonchick_new_version.Data_Layer.model.Vakans_information
import okhttp3.Response

interface PostService {
    suspend fun getAllPosts():List<Vakans_information>
}