package com.asykurkhamid.kitabisamovie.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asykurkhamid.kitabisamovie.R
import com.asykurkhamid.kitabisamovie.adapter.MovieAdapter
import com.asykurkhamid.kitabisamovie.model.BaseCategoryModel
import com.asykurkhamid.kitabisamovie.model.MovieModel
import com.asykurkhamid.kitabisamovie.presenter.MoviePresenter
import com.asykurkhamid.kitabisamovie.utils.BaseActivity
import com.asykurkhamid.kitabisamovie.utils.Constant
import com.asykurkhamid.kitabisamovie.utils.MovieView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.category_movie.view.*
import kotlinx.android.synthetic.main.custom_toolbar.*


class MainActivity : BaseActivity(),MovieView {

    private lateinit var presenter: MoviePresenter
    private lateinit var adapter: MovieAdapter
    private var movieList : ArrayList<MovieModel> = ArrayList()
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomSheetDialog = BottomSheetDialog(this)

        val tb = toolbar as Toolbar
        tvTitleToolbar.text = getString(R.string.cinemaku)
        setSupportActionBar(tb)
        tb.setNavigationIcon(null)

        presenter = MoviePresenter(this)
        presenter.getCategoryMovie(Constant.popular.toString())
        initRecycler()

        cardCategory.setOnClickListener { showBottomShetCategory() }

        btnFavoriteTb.setOnClickListener {
            startActivity(Intent(this,FavoriteActivity::class.java))
        }
    }

    private fun showBottomShetCategory() {
        val view = layoutInflater.inflate(R.layout.category_movie, null)
        val catPopular = view.tvCatPopular
        val catUpcoming = view.tvCatUpComing
        val catTopRated = view.tvCatToprated
        val catNowPlaying = view.tvNowPlaying
        catPopular.setOnClickListener {
            presenter.getCategoryMovie(Constant.popular.toString())
            if (bottomSheetDialog.isShowing){
                bottomSheetDialog.dismiss()
            }
        }
        catUpcoming.setOnClickListener {
            presenter.getCategoryMovie(Constant.upcoming.toString())
            if (bottomSheetDialog.isShowing){
                bottomSheetDialog.dismiss()
            }
        }
        catTopRated.setOnClickListener {
            presenter.getCategoryMovie(Constant.top_rated.toString())
            if (bottomSheetDialog.isShowing) {
                bottomSheetDialog.dismiss()
            }
        }
        catNowPlaying.setOnClickListener {
            presenter.getCategoryMovie(Constant.now_playing.toString())
            if (bottomSheetDialog.isShowing) {
                bottomSheetDialog.dismiss()
            }
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    private fun initRecycler() {
        adapter = MovieAdapter(movieList,this)
        rvMovie.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        rvMovie.adapter = adapter
    }

    override fun showLoading() {
        pgBar.visibility = View.VISIBLE
        rvMovie.visibility = View.GONE
    }

    override fun hideLoading() {
        pgBar.visibility = View.GONE
        rvMovie.visibility = View.VISIBLE
    }

    override fun showMovie(movies: BaseCategoryModel?) {
        if (movies != null) {
            movieList.clear()
            movieList.addAll(movies.results)
            adapter.setData(movies.results)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite,menu)

        return super.onCreateOptionsMenu(menu)
    }

}
