package tatyana.volkova.app.giphy.presentation.gif_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tatyana.volkova.app.giphy.domain.model.Gif
import tatyana.volkova.app.giphy.domain.usecase.GetAndSaveGifsUseCase
import tatyana.volkova.app.giphy.domain.usecase.ObserveGifsUseCase
import tatyana.volkova.app.giphy.domain.usecase.RemoveGifUseCase
import tatyana.volkova.app.giphy.domain.usecase._base.observer.SimpleDisposableCompletableObserver
import tatyana.volkova.app.giphy.domain.usecase._base.observer.SimpleDisposableObserver
import javax.inject.Inject

@HiltViewModel
class GifListViewModel @Inject constructor(
    private val getAndSaveGifsUseCase: GetAndSaveGifsUseCase,
    private val observeGifsUseCase: ObserveGifsUseCase,
    private val removeGifUseCase: RemoveGifUseCase
) : ViewModel() {

    private val list = MutableLiveData<List<Gif>>()
    fun getList(): LiveData<List<Gif>> = list

    //For pagination. Offset = page * limit
    private val limit = 20
    private var page = 0
    private var hasMore = true

    init {
        getGifsRemoteAndSaveToDb()
        observeGifsFromDb()
    }

    private fun getGifsRemoteAndSaveToDb() {
        getAndSaveGifsUseCase.execute(object : SimpleDisposableCompletableObserver() {
            override fun onComplete() {
                Log.e(TAG, "getAndSaveGifsUseCase onComplete")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, e.localizedMessage ?: e.stackTraceToString())
            }
        }, GetAndSaveGifsUseCase.Params(limit, page * limit))
    }

    private fun observeGifsFromDb() {
        observeGifsUseCase.execute(object : SimpleDisposableObserver<List<Gif>>() {
            override fun onNext(t: List<Gif>) {
                list.postValue(t)
                if (t.isEmpty().not()) {
                    hasMore = t.last().totalCount > t.last().offset
                }
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, e.localizedMessage ?: e.stackTraceToString())
            }
        })
    }

    fun removeGif(id: String) {
        removeGifUseCase.execute(object : SimpleDisposableCompletableObserver() {
            override fun onComplete() {
                Log.e(TAG, "deleteGif onComplete")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, e.localizedMessage ?: e.stackTraceToString())
            }
        }, RemoveGifUseCase.Params(id))
    }

    fun nextPage() {
        if (hasMore) {
            page++
            getGifsRemoteAndSaveToDb()
        }
    }

    override fun onCleared() {
        getAndSaveGifsUseCase.clear()
        observeGifsUseCase.clear()
        removeGifUseCase.clear()
        super.onCleared()
    }

    //For logs
    companion object {
        const val TAG = "GifListViewModel"
    }
}