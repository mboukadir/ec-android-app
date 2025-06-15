package fr.ec.app.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "post"
)
data class PostEntity(
    @PrimaryKey
    val id : String,
    @ColumnInfo(name = "post_title")
    val title: String,
    @ColumnInfo(name = "post_subtitle")
    val subtitle: String,
    @ColumnInfo(name = "post_image")
    val image : String,
)