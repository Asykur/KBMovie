package com.asykurkhamid.kitabisamovie.presenter

import com.asykurkhamid.kitabisamovie.model.BaseCategoryModel
import com.asykurkhamid.kitabisamovie.model.BaseReviewsModel
import com.asykurkhamid.kitabisamovie.model.MovieModel
import com.asykurkhamid.kitabisamovie.model.ReviewsModel
import com.asykurkhamid.kitabisamovie.retrofit.Services
import com.asykurkhamid.kitabisamovie.utils.ReviewsView
import com.google.gson.GsonBuilder
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Modifier
import java.net.HttpURLConnection

class ReviewsPresenterTest(){
    @Mock lateinit var view: ReviewsView
    private lateinit var presenter: ReviewsPresenter
    private var mockWebServer = MockWebServer()
    private lateinit var apiService: Services
    private val reviewList: List<ReviewsModel> = ArrayList()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = ReviewsPresenter(view)
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Services::class.java)
    }
    @After
    fun whenOnDestroy() {
        mockWebServer.shutdown()
    }

    @Test
    fun whenGetReviewsMovieSuccess(){
        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .create()
        val json = gson.toJson(MovieModel::class)

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(json)
        mockWebServer.enqueue(response)

        val call: Call<BaseReviewsModel> = apiService.callReviews(1,"")
        try {
            val testResponse: Response<BaseReviewsModel> = call.execute()
            Mockito.doNothing().`when`(view).hideLoading()
            assertTrue(testResponse.isSuccessful())
            assertTrue(testResponse.body() != null)
            Mockito.doNothing()
                .`when`(view).showReviews(BaseReviewsModel(1,reviewList,1,1))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}