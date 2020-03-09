package com.asykurkhamid.kitabisamovie.retrofit

import com.asykurkhamid.kitabisamovie.model.BaseCategoryModel
import com.asykurkhamid.kitabisamovie.model.BaseReviewsModel
import com.asykurkhamid.kitabisamovie.model.DetailMovieModel
import com.asykurkhamid.kitabisamovie.model.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface Services {

    @GET
    fun callMovies(
        @Url url: String,
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): Call<BaseCategoryModel>

    @GET("{movie_id}")
    fun callMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") key: String
    ): Call<MovieModel>

    @GET("{movie_id}/reviews")
    fun callReviews(
        @Path("movie_id")movie_id: Int,
        @Query("api_key") key: String
    ):Call<BaseReviewsModel>
}