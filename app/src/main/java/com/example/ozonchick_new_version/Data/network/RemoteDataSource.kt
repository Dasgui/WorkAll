package com.example.ozonchick_new_version.Data.network

class RemoteDataSource constructor(private val postService: PostService) {
    suspend fun getAllPosts() = postService.getAllPosts()
}