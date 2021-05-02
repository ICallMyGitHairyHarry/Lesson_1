package com.example.lesson_1.repository

import com.example.lesson_1.api.RetrofitInstance
import com.example.lesson_1.model.Album
import com.example.lesson_1.model.Post
import com.example.lesson_1.model.User
import retrofit2.Response

class Repository {
    suspend fun getAlbums(): Response<List<Album>> {
        return RetrofitInstance.api.getAlbums()
    }

    suspend fun getUsers(): Response<List<User>> {
        return RetrofitInstance.api.getUsers()
    }

    suspend fun pushPost(post: Post): Response<Post> {
        return RetrofitInstance.api.pushPost(post)
    }

    suspend fun pushPost2(userId: Int, id: Int, title: String, body: String): Response<Post> {
        return RetrofitInstance.api.pushPost2(userId, id, title, body)
    }
}