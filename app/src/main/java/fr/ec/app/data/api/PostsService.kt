package fr.ec.app.data.api

import retrofit2.Call
import retrofit2.http.GET

interface PostsService {
    @GET("posts/posts.json")
    suspend fun getPost() : PostsResponse
}