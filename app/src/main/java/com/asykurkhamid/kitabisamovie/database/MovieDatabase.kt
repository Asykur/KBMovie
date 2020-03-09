package com.asykurkhamid.kitabisamovie.database

import android.content.Context
import androidx.room.*
import com.asykurkhamid.kitabisamovie.model.MovieModel

@Database(entities = arrayOf(MovieModel::class), version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase() : RoomDatabase() {
    abstract fun moviesDAO(): MovieDAO

    companion object {
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase? {
            if (instance == null) {
                synchronized(MovieDatabase::class) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            MovieDatabase::class.java, "moviedata.db"
                        )
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance == null
        }
    }
}