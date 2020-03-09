package com.asykurkhamid.kitabisamovie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BaseReviewsModel(
    val page: Int,
    val results: List<ReviewsModel>,
    val total_results: Int,
    val total_pages: Int
):Parcelable