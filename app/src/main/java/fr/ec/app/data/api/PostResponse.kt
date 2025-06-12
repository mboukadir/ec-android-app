package fr.ec.app.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
@JsonClass(generateAdapter = true)
data class PostsResponse(
    val posts: List<PostResponse>
)
@JsonClass(generateAdapter = true)
data class PostResponse(
    val id: String,
    @Json(name = "name")
    val title : String,
    val tagline: String,
    val thumbnail: Thumbnail,
    val votesCount:Int,
    val commentsCount: Int
)
@JsonClass(generateAdapter = true)
data class Thumbnail(val url: String)
