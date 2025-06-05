package fr.ec.app.data

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fr.ec.app.R
import fr.ec.app.data.api.PostsResponse
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


object DataProvider {

    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private val API_URL = "https://raw.githubusercontent.com/mboukadir/ec-android-app/refs/heads/main/data/posts/posts.json"

    fun getPosts(
        onResponse: (List<Post>) -> Unit
    ) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(API_URL)
            .get()
            .build()

        val posts = mutableListOf<Post>()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("DataProvider", "${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val json =  response.body!!.string()
                val adapter = moshi.adapter(PostsResponse::class.java)
                val postsResponse = adapter.fromJson(json)

                val result = postsResponse.map()
                onResponse(result)
            }

        })
    }

    private fun PostsResponse?.map() : List<Post> = this?.posts.orEmpty().map { postResponse ->
        Post(
            image = 0,
            title = postResponse.title,
            subtitle = postResponse.tagline
        )
    }
}