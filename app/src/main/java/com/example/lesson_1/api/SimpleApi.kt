package com.example.lesson_1.api

import com.example.lesson_1.model.Album
import com.example.lesson_1.model.Post
import com.example.lesson_1.model.User
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

    @GET("albums")
    suspend fun getAlbums(): Response<List<Album>>

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @POST("posts")
    suspend fun pushPost(
        @Body post: Post
    ): Response<Post>

    @FormUrlEncoded
    @POST("post")
    suspend fun pushPost2(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Response<Post>
}