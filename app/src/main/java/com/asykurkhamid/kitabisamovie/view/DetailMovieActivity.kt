package com.asykurkhamid.kitabisamovie.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asykurkhamid.kitabisamovie.BuildConfig
import com.asykurkhamid.kitabisamovie.R
import com.asykurkhamid.kitabisamovie.adapter.ReviewAdapter
import com.asykurkhamid.kitabisamovie.database.MovieDatabase
import com.asykurkhamid.kitabisamovie.model.BaseReviewsModel
import com.asykurkhamid.kitabisamovie.model.MovieModel
import com.asykurkhamid.kitabisamovie.model.ReviewsModel
import com.asykurkhamid.kitabisamovie.presenter.DatabasePresenter
import com.asykurkhamid.kitabisamovie.presenter.DetailMoviePresenter
import com.asykurkhamid.kitabisamovie.presenter.ReviewsPresenter
import com.asykurkhamid.kitabisamovie.utils.*
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.custom_toolbar.*


class DetailMovieActivity : BaseActivity(), DetailMovieView, ReviewsView, SetDBView {

    private lateinit var detailMoviePresenter: DetailMoviePresenter
    private lateinit var dbPresenter: DatabasePresenter
    private var movieDB: MovieDatabase? = null
    private lateinit var revPresenter: ReviewsPresenter
    private lateinit var reviewAdapter: ReviewAdapter
    private var reviewList: ArrayList<ReviewsModel> = ArrayList()
    private lateinit var movie: MovieModel
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        btnFavoriteTb.visibility = View.GONE
        val tb = toolbar as Toolbar
        setSupportActionBar(tb)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        tb.setNavigationOnClickListener { finish() }

        movieDB = MovieDatabase.getInstance(this)

        val id = intent.getIntExtra("movie_id", 0);
        detailMoviePresenter = DetailMoviePresenter(this)
        dbPresenter = DatabasePresenter(null, this, movieDB)
        detailMoviePresenter.getDetailMovie(id)

        revPresenter = ReviewsPresenter((this))
        revPresenter.getReviews(id)
        initRecycler()

        btnFavoriteDetail.setOnClickListener {
            if (!btnFavoriteDetail.isChecked) {
                dbPresenter.deleteFromLocal(movie)
            } else {
                dbPresenter.saveMovieToLocal(movie)
            }
        }
        dbPresenter.findByIdMovie(id)

    }

    private fun initRecycler() {
        reviewAdapter = ReviewAdapter(reviewList)
        rvReviews.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvReviews.adapter = reviewAdapter
    }

    override fun showLoading() {
        pgDetail.visibility = View.VISIBLE
        cardItem.visibility = View.GONE
        rvReviews.visibility = View.GONE
        layoutEmptyReview.visibility = View.GONE
        tvReviewTitle.visibility = View.GONE
    }

    override fun hideLoading() {
        pgDetail.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        Snackbar.make(tvDescMovieDetail, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun updateBtnFav(isChecked: Boolean) {
        if (isChecked) {
            btnFavoriteDetail.setChecked(true)
        }else{
            btnFavoriteDetail.setChecked(false)
        }
    }

    override fun showReviews(movies: BaseReviewsModel?) {
        if (movies != null && movies.results.size > 0) {
            reviewList.clear()
            reviewList.addAll(movies.results)
            rvReviews.visibility = View.VISIBLE
            tvReviewTitle.visibility =  View.VISIBLE
        } else {
            layoutEmptyReview.visibility = View.VISIBLE
            tvReviewTitle.visibility =  View.VISIBLE
        }
        reviewAdapter.notifyDataSetChanged()
    }

    override fun showMovie(movies: MovieModel?) {
        if (movies != null) {
            movie = movies
            id = movies.id
            tvTitleToolbar.text = movies.title
            if (!isFinishing && !isDestroyed) {
                Glide.with(this).load(BuildConfig.BASE_URL_IMG185 + movies.poster_path)
                    .into(imgMovieDetail)
            }
            tvDescMovieDetail.text = movies.overview
            tvReleaseDateDetail.text = toGMTFormat(movies.release_date)
            tvTitleMovieDetail.text = movies.title
            cardItem.visibility = View.VISIBLE
        } else {
            cardItem.visibility = View.GONE
        }
    }

}
