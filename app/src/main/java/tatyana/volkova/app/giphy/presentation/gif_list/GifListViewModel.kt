package tatyana.volkova.app.giphy.presentation.gif_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tatyana.volkova.app.giphy.domain.model.Gif
import tatyana.volkova.app.giphy.domain.model.Request
import tatyana.volkova.app.giphy.domain.usecase.GetAndSaveObservableUseCase
import tatyana.volkova.app.giphy.domain.usecase.ObserveGifsWithQueryUseCase
import tatyana.volkova.app.giphy.domain.usecase.ObserveTableUseCase
import tatyana.volkova.app.giphy.domain.usecase.RemoveGifUseCase
import tatyana.volkova.app.giphy.domain.usecase._base.observer.SimpleDisposableCompletableObserver
import tatyana.volkova.app.giphy.domain.usecase._base.observer.SimpleDisposableObserver
import javax.inject.Inject

@HiltViewModel
class GifListViewModel @Inject constructor(
    private val observeGifsUseCase: ObserveTableUseCase,
    private val getAndSaveGifsUseCase: GetAndSaveObservableUseCase,
    private val observeGifsWithQueryUseCase: ObserveGifsWithQueryUseCase,
    private val removeGifUseCase: RemoveGifUseCase
) : ViewModel() {

    private val list = MutableLiveData<List<Gif>>()
    fun getList(): LiveData<List<Gif>> = list

    //For pagination. Offset = page * limit
    private val limit = 20
    private var page = 0
    private var hasMore = true

    //For search
    private val searchSubject = PublishSubject.create<Request>()
    private var searchQuery: String? = null

    init {
        getGifsRemoteAndSaveToDb()
//        observeGifsFromDb()
        observeGifs()
        searchSubject.onNext(
            Request(
                limit = limit,
                offset = page * limit,
                query = ""
            )
        )
    }

    private fun observeGifs() {
        viewModelScope.launch(Dispatchers.IO) {
            observeGifsUseCase.buildUseCase().collect {
                list.postValue(it)
            }
        }
    }

    private fun getGifsRemoteAndSaveToDb() {
        getAndSaveGifsUseCase.execute(object : SimpleDisposableObserver<List<Long>>() {

            override fun onNext(t: List<Long>) {
                Log.e(TAG, "getAndSaveGifsUseCase onNext")
            }

            override fun onComplete() {
                Log.e(TAG, "getAndSaveGifsUseCase onComplete")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, e.localizedMessage ?: e.stackTraceToString())
            }
        }, GetAndSaveObservableUseCase.Params(searchSubject))
    }

    fun createObserveRequest(query: String) {
        page = 0
        hasMore = true
        searchQuery = query
        searchSubject.onNext(
            Request(
                limit = limit,
                offset = page * limit,
                query = query
            )
        )
    }

    private fun observeGifsFromDb() {
        observeGifsWithQueryUseCase.execute(object : SimpleDisposableObserver<List<Gif>>() {
            override fun onNext(t: List<Gif>) {
                list.postValue(t)
                if (t.isEmpty().not()) {
                    hasMore = t.last().totalCount > t.last().offset
                }
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, e.localizedMessage ?: e.stackTraceToString())
            }
        }, ObserveGifsWithQueryUseCase.Params(searchSubject))
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
            searchSubject.onNext(
                Request(
                    limit = limit,
                    offset = page * limit,
                    query = searchQuery
                )
            )
        }
    }

    override fun onCleared() {
        getAndSaveGifsUseCase.clear()
        observeGifsWithQueryUseCase.clear()
        removeGifUseCase.clear()
        super.onCleared()
    }

    //For logs
    companion object {
        const val TAG = "GifListViewModel"
    }
}