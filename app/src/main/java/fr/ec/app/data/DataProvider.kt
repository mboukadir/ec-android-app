package fr.ec.app.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import fr.ec.app.data.api.PostsResponse
import fr.ec.app.data.api.PostsService
import fr.ec.app.data.database.AppDataBase
import fr.ec.app.data.database.entities.PostEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class DataProvider(
    val context: Context
) {
    private val BASE_URL =
        "https://raw.githubusercontent.com/mboukadir/ec-android-app/refs/heads/main/data/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val database: AppDataBase = Room.databaseBuilder(
        context = context,
        klass = AppDataBase::class.java,
        name = "database-name"
    ).build()


    private val service: PostsService = retrofit.create(PostsService::class.java)


    suspend fun getPosts(): Result = withContext(Dispatchers.Default) {

        try {
            val postsResponse = service.getPost()
            save(postsResponse)
            Result.Success(postsResponse.map())
        } catch (e: Exception) {
            Log.e("PostRepository", "Error fetching posts from service", e)

            val postEntities = database.postDao().getPosts()
            if (postEntities.isEmpty()) {
                Result.Failure
            } else {
                Result.Success(posts = postEntities.map {
                    Post(
                        title = it.title,
                        subtitle = it.subtitle,
                        image = it.image
                    )
                })
            }
        }

    }

    private fun save(postsResponse: PostsResponse) {
        val postEntities = postsResponse.posts.map {
            PostEntity(
                id = it.id,
                title = it.title,
                subtitle = it.tagline,
                image = it.thumbnail.url
            )
        }

        database.postDao().insert(postEntities)
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