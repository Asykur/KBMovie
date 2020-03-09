package com.asykurkhamid.kitabisamovie.presenter

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.asykurkhamid.kitabisamovie.database.MovieDAO
import com.asykurkhamid.kitabisamovie.database.MovieDatabase
import com.asykurkhamid.kitabisamovie.model.MovieModel
import com.asykurkhamid.kitabisamovie.utils.DBView
import com.asykurkhamid.kitabisamovie.utils.SetDBView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DatabasePresenterTest {
    private lateinit var movieDao: MovieDAO
    private lateinit var db: MovieDatabase
    private lateinit var movieModel: MovieModel
    @Mock lateinit var setView: SetDBView
    @Mock lateinit var view: DBView
    private lateinit var presenter: DatabasePresenter
    private val movieList: List<MovieModel> = ArrayList()

    @Before
    public fun setup() {
        MockitoAnnotations.initMocks(this)
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, MovieDatabase::class.java
        ).build()
        presenter = DatabasePresenter(view, setView, db)

        movieDao = db.moviesDAO()
        movieModel = MovieModel(0, "", true, "", "", ArrayList(), "", "", "")
    }

    @Test
    @Throws(Exception::class)
    fun whenDofindByIdMovieSuccess() {
        GlobalScope.launch {
            movieDao.insert(movieModel)
            movieModel = movieDao.findByTitle(movieModel.id)
        }
        assertNotNull(movieModel)
        doNothing().`when`(setView).updateBtnFav(true)
    }

    @Test
    fun whenDodeleteFromLocalSuccess(){
        GlobalScope.launch {
            movieDao.delete(movieModel)
        }
        doNothing().`when`(setView).showMessage("Success")
    }

    @Test
    fun whenSaveMovieToLocalSuccess(){
        GlobalScope.launch {
            movieDao.insert(movieModel)
        }
        doNothing().`when`(setView).showMessage("Success")
    }

    @Test
    fun whenDoGetLocalMovieSuccess(){
        doNothing().`when`(view).showLoading()
        GlobalScope.launch {
            movieDao.getAll()
        }
        doNothing().`when`(view).showMovie(movieList)
    }

    @After
    fun whenDestroyed() {
        db.close()
    }
}