package fr.ec.app.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.ec.app.R
import fr.ec.app.data.Post

class PostAdapter() : RecyclerView.Adapter<PostViewHolder>() {

    private val dataSource = mutableListOf<Post>()

    fun show(posts : List<Post>) {
        dataSource.addAll(posts)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        Log.d("PostAdapter", "onCreateViewHolder")
        return PostViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int =
        if (position == 0) R.layout.post_item_big_view else R.layout.post_item_view


    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        Log.d("PostAdapter", "onBindViewHolder[$position]")

        val post = dataSource[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = dataSource.size

    companion object {
        private const val FIRST_ITEM = 0
        private const val OTHER = 1
    }
}

class PostViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private val postImageView = itemView.findViewById<ImageView>(R.id.post_image)
    private val titleTextView = itemView.findViewById<TextView>(R.id.post_title)
    private val subtitleTextView = itemView.findViewById<TextView>(R.id.post_subtitle)

    fun bind(post: Post) {
        // biding
        Picasso.get().load(post.image)
            .placeholder(R.mipmap.ic_launcher)
            .into(postImageView)
        titleTextView.text = post.title
        subtitleTextView.text = post.subtitle
    }
}