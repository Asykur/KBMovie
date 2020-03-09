//package com.asykurkhamid.kitabisamovie.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.asykurkhamid.kitabisamovie.BuildConfig
//import com.asykurkhamid.kitabisamovie.model.BaseModel
//import com.asykurkhamid.kitabisamovie.retrofit.ServiceFactory
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.disposables.Disposable
//import io.reactivex.schedulers.Schedulers
//
//class FavoriteViewModel : ViewModel() {
//    var favMovie: MutableLiveData<BaseModel>? = null
//    val disposable: CompositeDisposable = CompositeDisposable()
//    var loadingProgress = MutableLiveData<Boolean>()
//
//
//    fun getFavMovies(): LiveData<BaseModel> {
//        if (favMovie == null) {
//            favMovie = MutableLiveData()
//            loadFavMovie(BuildConfig.API_KEY, 1)
//        }
//        return favMovie as MutableLiveData<BaseModel>
//    }
//
//    fun loadFavMovie(key: String, page: Int) {
//        loadingProgress.postValue(true)
//        val api = ServiceFactory().instanceServices().getPopular(key, page)
//        api.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : io.reactivex.Observer<BaseModel> {
//                override fun onComplete() {
//                    TODO("Not yet implemented")
//                }
//
//                override fun onSubscribe(d: Disposable) {
//                    disposable.add(d)
//                }
//
//                override fun onNext(t: BaseModel) {
//                    favMovie?.postValue(t)
//                    loadingProgress.postValue(false)
//                }
//
//                override fun onError(e: Throwable) {
//                    favMovie?.postValue(null)
//                    loadingProgress.postValue(false)
//                }
//
//            })
//    }
//
//    fun observeFavMovie(): MutableLiveData<BaseModel>? {
//        return favMovie
//    }
//
//    fun observeLoading(): LiveData<Boolean> {
//        return loadingProgress
//    }
//
//}