package com.asykurkhamid.kitabisamovie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewsModel(
    val author: String,
    val content: String,
    val id: String,
    val url: String
):Parcelable