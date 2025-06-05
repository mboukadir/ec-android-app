package fr.ec.app.data.api

import com.squareup.moshi.Json

/**
 * ---
 * {
 *   "posts": [
 *     {
 *       "id": "968597",
 *       "name": "Embedded iPaaS from Albato",
 *       "tagline": "Make your SaaS stick with AI-fueled embedded iPaaS",
 *       "thumbnail": {
 *         "url": "https://ph-files.imgix.net/b6e8453d-1d6d-4392-a76a-f3923d87602a.gif?auto=format"
 *       },
 *       "votesCount": 392,
 *       "commentsCount": 60
 *     }]
 *   }
 */
data class PostsResponse(
    val posts: List<PostResponse>
)

data class PostResponse(
    val id: String,
    @Json(name = "name")
    val title : String,
    val tagline: String,
    val thumbnail: Thumbnail,
    val votesCount:Int,
    val commentsCount: Int
)

data class Thumbnail(val url: String)
