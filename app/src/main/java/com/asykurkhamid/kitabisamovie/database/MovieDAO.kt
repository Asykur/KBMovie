package com.asykurkhamid.kitabisamovie.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.asykurkhamid.kitabisamovie.model.MovieModel
import io.reactivex.Observable
import java.util.*

@Dao
interface MovieDAO {
    @Query("SELECT * from movies_table")
    fun getAll(): List<MovieModel>

    @Query("SELECT * FROM movies_table WHERE id LIKE :id")
    fun findByTitle(id: Int): MovieModel

    @Insert(onConflict = REPLACE)
    fun insert(student: MovieModel)

    @Delete
    fun delete(student: MovieModel)
}