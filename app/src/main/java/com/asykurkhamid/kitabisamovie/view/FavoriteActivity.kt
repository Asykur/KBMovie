package com.asykurkhamid.kitabisamovie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asykurkhamid.kitabisamovie.R
import com.asykurkhamid.kitabisamovie.adapter.MovieAdapter
import com.asykurkhamid.kitabisamovie.database.MovieDatabase
import com.asykurkhamid.kitabisamovie.model.MovieModel
import com.asykurkhamid.kitabisamovie.presenter.DatabasePresenter
import com.asykurkhamid.kitabisamovie.utils.BaseActivity
import com.asykurkhamid.kitabisamovie.utils.DBView
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_favorite.toolbar
import kotlinx.android.synthetic.main.custom_toolbar.*
import kotlinx.coroutines.launch

class FavoriteActivity : BaseActivity(),DBView {
    private var movieDB:MovieDatabase? = null
    private lateinit var presenter: DatabasePresenter
    var list: ArrayList<MovieModel> = ArrayList()
    lateinit var adapter: MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val tb = toolbar as Toolbar
        setSupportActionBar(tb)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        tb.setNavigationOnClickListener { finish() }
        tvTitleToolbar.text = getString(R.string.favorite_movie)
        btnFavoriteTb.visibility = View.GONE

        movieDB = MovieDatabase.getInstance(this)
        //getMovie from DB
        presenter = DatabasePresenter(this,null,movieDB)

    }

    override fun onResume() {
        super.onResume()
        presenter.getLocalMovie()
        initRecycler()
    }

    private fun initRecycler() {
        adapter = MovieAdapter(list,this)
        rvFavorite.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        rvFavorite.adapter = adapter
    }

    override fun showLoading() {
        pgFav.visibility = View.VISIBLE
        rvFavorite.visibility = View.GONE
    }

    override fun hideLoading() {
        pgFav.visibility = View.GONE
    }

    override fun showMovie(movies: List<MovieModel>?) {
       if (movies != null){
           list.clear()
           list.addAll(movies)
           adapter.setData(movies)
           adapter.notifyDataSetChanged()
           rvFavorite.visibility = View.VISIBLE
       }else{
           rvFavorite.visibility = View.GONE
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        MovieDatabase.destroyInstance()
    }
}
