package com.asykurkhamid.kitabisamovie.utils

import com.asykurkhamid.kitabisamovie.model.BaseCategoryModel
import com.asykurkhamid.kitabisamovie.model.BaseReviewsModel
import com.asykurkhamid.kitabisamovie.model.ReviewsModel

interface ReviewsView {
    fun showLoading()
    fun hideLoading()
    fun showReviews(movies: BaseReviewsModel?)
}