package com.asykurkhamid.kitabisamovie.utils

import com.asykurkhamid.kitabisamovie.model.MovieModel

interface DBView {
    fun showLoading()
    fun hideLoading()
    fun showMovie(movies: List<MovieModel>?)

}