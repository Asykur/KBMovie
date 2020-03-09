package com.asykurkhamid.kitabisamovie.presenter

import com.asykurkhamid.kitabisamovie.BuildConfig
import com.asykurkhamid.kitabisamovie.model.DetailMovieModel
import com.asykurkhamid.kitabisamovie.model.MovieModel
import com.asykurkhamid.kitabisamovie.retrofit.ServiceFactory
import com.asykurkhamid.kitabisamovie.utils.DetailMovieView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMoviePresenter(private val view: DetailMovieView) {

    fun getDetailMovie(id: Int) {
        view.showLoading()
        val api = ServiceFactory().instanceServices()
        val callApi = api.callMovieDetail(id, BuildConfig.API_KEY)
        callApi.enqueue(object : Callback<MovieModel> {
            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                view.hideLoading()
            }

            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                view.hideLoading()
                if (response.isSuccessful && response.body() != null) {
                    view.showMovie(response.body())
                }
            }

        })
    }
}