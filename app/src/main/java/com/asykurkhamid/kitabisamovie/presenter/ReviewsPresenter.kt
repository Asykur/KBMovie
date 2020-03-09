package com.asykurkhamid.kitabisamovie.presenter

import com.asykurkhamid.kitabisamovie.BuildConfig
import com.asykurkhamid.kitabisamovie.model.BaseReviewsModel
import com.asykurkhamid.kitabisamovie.retrofit.ServiceFactory
import com.asykurkhamid.kitabisamovie.utils.ReviewsView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewsPresenter(private val view: ReviewsView) {

    fun getReviews(id: Int) {
        view.showLoading()
        val api = ServiceFactory().instanceServices()
        val callApi = api.callReviews(id, BuildConfig.API_KEY)
        callApi.enqueue(object : Callback<BaseReviewsModel>{
            override fun onFailure(call: Call<BaseReviewsModel>, t: Throwable) {
                view.hideLoading()
            }

            override fun onResponse(
                call: Call<BaseReviewsModel>,
                response: Response<BaseReviewsModel>
            ) {
                view.hideLoading()
                if (response.isSuccessful && response.body() != null){
                    view.showReviews(response.body())
                }
            }

        })
    }
}