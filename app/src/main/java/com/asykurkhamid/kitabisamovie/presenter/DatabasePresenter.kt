package com.asykurkhamid.kitabisamovie.presenter

import com.asykurkhamid.kitabisamovie.database.MovieDatabase
import com.asykurkhamid.kitabisamovie.model.MovieModel
import com.asykurkhamid.kitabisamovie.utils.DBView
import com.asykurkhamid.kitabisamovie.utils.SetDBView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

class DatabasePresenter(
    private val view: DBView?,
    private val setView: SetDBView?,
    private val movieDB: MovieDatabase?
) {

    var exist: MovieModel? = null
    var listMovie: List<MovieModel>? = null


    fun getLocalMovie() {
        view?.showLoading()
        GlobalScope.launch {
            listMovie = movieDB?.moviesDAO()?.getAll()

            withContext(Dispatchers.Main) {
                view?.showMovie(listMovie)
            }
        }
        view?.hideLoading()

    }

    fun saveMovieToLocal(movie: MovieModel) {
        GlobalScope.launch {
            movieDB?.moviesDAO()?.insert(movie)
            withContext(Dispatchers.Main){
                setView?.showMessage("Added to favorites successfully")
            }
        }
    }

    fun deleteFromLocal(movie: MovieModel) {
        GlobalScope.launch {
            movieDB?.moviesDAO()?.delete(movie)
            withContext(Dispatchers.Main){
                setView?.showMessage("Delete from favorites successfully")
            }
        }
    }

    fun findByIdMovie(id: Int) {
        GlobalScope.launch {
            exist = movieDB?.moviesDAO()?.findByTitle(id)
            if (exist != null){
                withContext(Dispatchers.Main){
                    setView?.updateBtnFav(true)
                }
            }else{
                withContext(Dispatchers.Main){
                    setView?.updateBtnFav(false)
                }
            }
        }
    }
}