package com.asykurkhamid.kitabisamovie.presenter

import com.asykurkhamid.kitabisamovie.BuildConfig
import com.asykurkhamid.kitabisamovie.model.BaseCategoryModel
import com.asykurkhamid.kitabisamovie.retrofit.ServiceFactory
import com.asykurkhamid.kitabisamovie.utils.MovieView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(private val view: MovieView) {

    fun getCategoryMovie(endPoint: String) {
        view.showLoading()
        val api = ServiceFactory().instanceServices()
        val callApi = api.callMovies(BuildConfig.BASE_URL + endPoint, BuildConfig.API_KEY, 1)

        callApi.enqueue(object : Callback<BaseCategoryModel> {
            override fun onFailure(call: Call<BaseCategoryModel>, t: Throwable) {
                view.hideLoading()
            }

            override fun onResponse(call: Call<BaseCategoryModel>, response: Response<BaseCategoryModel>) {
                view.hideLoading()
                if (response.isSuccessful && response.body() != null) {
                    view.showMovie(response.body())
                }
            }
        })
    }
}