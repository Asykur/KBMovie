package com.asykurkhamid.kitabisamovie.utils

import com.asykurkhamid.kitabisamovie.model.BaseCategoryModel

interface MovieView {
    fun showLoading()
    fun hideLoading()
    fun showMovie(movies: BaseCategoryModel?)
}