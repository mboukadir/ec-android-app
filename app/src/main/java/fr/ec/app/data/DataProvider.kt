package fr.ec.app.data

import fr.ec.app.R

object DataProvider {

    fun getPosts(): List<Post> {
        val posts = mutableListOf<Post>()
        repeat(100_000) { index ->
            val post = Post(
                image = R.mipmap.ic_launcher,
                title = "title $index",
                subtitle = "Subtitle $index"
            )
            posts.add(post)
        }

        return posts
    }
}