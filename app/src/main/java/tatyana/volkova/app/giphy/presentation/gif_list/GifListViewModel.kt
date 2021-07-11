package tatyana.volkova.app.giphy.presentation.gif_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.subjects.PublishSubject
import tatyana.volkova.app.giphy.domain.model.Gif
import tatyana.volkova.app.giphy.domain.model.Request
import tatyana.volkova.app.giphy.domain.model._base.ResultEntity
import tatyana.volkova.app.giphy.domain.usecase.GetAndSaveObservableUseCase
import tatyana.volkova.app.giphy.domain.usecase.ObserveGifsWithQueryUseCase
import tatyana.volkova.app.giphy.domain.usecase.RemoveGifUseCase
import tatyana.volkova.app.giphy.domain.usecase._base.observer.SimpleDisposableCompletableObserver
import tatyana.volkova.app.giphy.domain.usecase._base.observer.SimpleDisposableObserver
import tatyana.volkova.app.giphy.presentation.ProgressState
import javax.inject.Inject

interface IGifListViewModel {
    val progressData: LiveData<ProgressState>
    fun removeGif(id: String)
}

@HiltViewModel
class GifListViewModel @Inject constructor(
    private val getAndSaveGifsUseCase: GetAndSaveObservableUseCase,
    private val observeGifsWithQueryUseCase: ObserveGifsWithQueryUseCase,
    private val removeGifUseCase: RemoveGifUseCase
) : ViewModel(), IGifListViewModel {

    private val list = MutableLiveData<List<Gif>>()
    fun getList(): LiveData<List<Gif>> = list

    //For pagination, offset = page * limit
    private val limit = 20
    private var page = 0
    private var hasMore = true

    //For search
    private val searchSubject = PublishSubject.create<Request>()
    private var searchQuery: String? = null

    //To show or hide progress
    override val progressData = MutableLiveData(ProgressState.FINISHED)

    //To show message
    private val message = MutableLiveData<String>()
    fun getMessage(): LiveData<String> = message

    init {
        getGifsRemoteAndSaveToDb()
        observeGifsFromDb()
        searchSubject.onNext(
            Request(
                limit = limit,
                offset = page * limit,
                query = ""
            )
        )
    }

    private fun getGifsRemoteAndSaveToDb() {
        getAndSaveGifsUseCase.execute(object : SimpleDisposableObserver<ResultEntity<List<Long>>>() {

            override fun onNext(result: ResultEntity<List<Long>>) {
                when(result) {
                    is ResultEntity.ERROR -> {
                        Log.e(TAG, "getAndSaveGifsUseCase ResultEntity.ERROR")
                        progressData.postValue(ProgressState.FINISHED)
                        message.postValue(result.error?.localizedMessage ?: "Error")
                    }
                    is ResultEntity.Empty -> {
                        Log.e(TAG, "getAndSaveGifsUseCase ResultEntity.Empty")
                        progressData.postValue(ProgressState.FINISHED)
                        message.postValue("No More Data")
                    }
                    is ResultEntity.LOADING -> {
                        Log.e(TAG, "getAndSaveGifsUseCase ResultEntity.LOADING")
                        progressData.postValue(ProgressState.LOADING)
                    }
                    is ResultEntity.SUCCESS -> {
                        Log.e(TAG, "getAndSaveGifsUseCase ResultEntity.SUCCESS")
                    }
                }
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
            override fun onNext(result: List<Gif>) {
                list.postValue(result)
                progressData.postValue(ProgressState.FINISHED)
                if (result.isEmpty().not()) {
                    hasMore = result.last().totalCount > result.last().offset
                }
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, e.localizedMessage ?: e.stackTraceToString())
            }
        }, ObserveGifsWithQueryUseCase.Params(searchSubject))
    }

    override fun removeGif(id: String) {
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