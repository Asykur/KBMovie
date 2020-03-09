package com.asykurkhamid.kitabisamovie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asykurkhamid.kitabisamovie.BuildConfig
import com.asykurkhamid.kitabisamovie.R
import com.asykurkhamid.kitabisamovie.model.MovieModel
import com.asykurkhamid.kitabisamovie.view.DetailMovieActivity
import com.asykurkhamid.kitabisamovie.view.FavoriteActivity
import com.asykurkhamid.kitabisamovie.view.MainActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.moview_item.view.*

class MovieAdapter(private var movieList: List<MovieModel>, private val ctx: Context) :
    RecyclerView.Adapter<MovieAdapter.VH>() {

    fun setData(movie: List<MovieModel>) {
        movieList = movie

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.moview_item, parent, false)
        return VH(itemView)
    }

    override fun getItemCount(): Int {
        if (movieList == null) {
            return 0
        } else {
            return movieList.size
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(movieList.get(position), ctx)
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val img = itemView.imgMovie
        private val title = itemView.tvTitleMovie
        private val relase = itemView.tvReleaseDate
        private val desc = itemView.tvDescMovie
        private lateinit var movies: MovieModel

        init {
            itemView.cardItem.setOnClickListener(this)
        }

        fun bindData(movie: MovieModel, context: Context) {
            movies = movie
            val imgPath = BuildConfig.BASE_URL_IMG185 + movie.poster_path
            Glide.with(context).load(imgPath).into(img)
            title.text = movie.title
            var date: String = ""
            if (context is MainActivity) {
                date = (context as MainActivity).toGMTFormat(movie.release_date).toString()
            } else {
                date = (context as FavoriteActivity).toGMTFormat(movie.release_date).toString()
            }
            relase.text = date
            desc.text = movie.overview
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.cardItem -> toDetailPage()
            }
        }

        private fun toDetailPage() {
            val intent = Intent(img.context, DetailMovieActivity::class.java)
            intent.putExtra("movie_id", movies.id)
            img.context.startActivity(intent)
        }
    }

}