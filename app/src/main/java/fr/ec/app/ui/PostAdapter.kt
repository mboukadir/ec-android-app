package fr.ec.app.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import fr.ec.app.R
import fr.ec.app.data.Post

class PostAdapter( private val dataSource: List<Post>): RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        Log.d("PostAdapter","onCreateViewHolder")
       return PostViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_item_view,parent,false)
        )
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        Log.d("PostAdapter","onBindViewHolder[$position]")

        val post = dataSource[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = dataSource.size
}

class PostViewHolder(
    itemView: View
): RecyclerView.ViewHolder(itemView) {
    private val postImageView = itemView.findViewById<ImageView>(R.id.post_image)
    private val titleTextView = itemView.findViewById<TextView>(R.id.post_title)
    private val subtitleTextView = itemView.findViewById<TextView>(R.id.post_subtitle)

    fun bind(post : Post) {
        // biding
        titleTextView.text = post.title
        subtitleTextView.text = post.subtitle
        postImageView.setImageResource(post.image)

    }
}