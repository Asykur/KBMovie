package com.asykurkhamid.kitabisamovie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailMovieModel(
    val id: Int,
    val poster_path: String,
    val title: String,
    val release_date: String,
    val overview: String
):Parcelable