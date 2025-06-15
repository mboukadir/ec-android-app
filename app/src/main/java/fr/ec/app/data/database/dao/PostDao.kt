package fr.ec.app.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.ec.app.data.database.entities.PostEntity

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : List<PostEntity>)
    @Query("Select * FROM post")
    fun getPosts() : List<PostEntity>

}