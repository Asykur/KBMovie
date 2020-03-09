package com.asykurkhamid.kitabisamovie.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies_table")
data class MovieModel(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "poster_path") val poster_path: String,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "release_date") val release_date: String,
    @ColumnInfo(name = "genre_ids") val genre_ids: List<Int>,
    @ColumnInfo(name = "original_title") val original_title: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String
) : Parcelable