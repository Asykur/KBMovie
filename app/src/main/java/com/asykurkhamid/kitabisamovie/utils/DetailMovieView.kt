package com.asykurkhamid.kitabisamovie.utils

import com.asykurkhamid.kitabisamovie.model.DetailMovieModel
import com.asykurkhamid.kitabisamovie.model.MovieModel

interface DetailMovieView {
    fun showLoading()
    fun hideLoading()
    fun showMovie(movies: MovieModel?)
}