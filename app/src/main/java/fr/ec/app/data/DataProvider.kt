package fr.ec.app.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fr.ec.app.data.api.PostsResponse
import fr.ec.app.data.api.PostsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object DataProvider {
    private val BASE_URL =
        "https://raw.githubusercontent.com/mboukadir/ec-android-app/refs/heads/main/data/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service: PostsService = retrofit.create(PostsService::class.java)


    suspend fun getPosts(): Result = withContext(Dispatchers.Default) {

        try {
            val postsResponse = service.getPost()
            Result.Success(
                posts = postsResponse.map()
            )

        } catch (e: Exception) {
            Result.Failure
        }

    }

    private fun PostsResponse?.map(): List<Post> = this?.posts.orEmpty().map { postResponse ->
        Post(
            image = postResponse.thumbnail.url,
            title = postResponse.title,
            subtitle = postResponse.tagline
        )
    }

    sealed class Result {
        object Failure : Result()
        data class Success(val posts: List<Post>) : Result()
    }

}