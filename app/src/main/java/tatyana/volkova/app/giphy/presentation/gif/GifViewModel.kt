package tatyana.volkova.app.giphy.presentation.gif

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tatyana.volkova.app.giphy.domain.model.Gif
import tatyana.volkova.app.giphy.domain.usecase.ObserveGifsUseCase
import tatyana.volkova.app.giphy.domain.usecase._base.observer.SimpleDisposableObserver
import tatyana.volkova.app.giphy.presentation.gif_list.GifListViewModel
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(
    private val observeGifsUseCase: ObserveGifsUseCase
) : ViewModel() {

    private val pair = MutableLiveData<Pair<List<Gif>, Int>>()
    fun getPair(): LiveData<Pair<List<Gif>, Int>> = pair

    private var currentGif: Gif? = null
    private var gifs: List<Gif>? = null

    fun setGif(gif: Gif) {
        currentGif = gif
        gifs?.let { gifs ->
            val position = gifs.indexOf(gif)
            pair.postValue(Pair(gifs, position))
        }
    }

    init {
        observeGifsFromDb()
    }

    private fun observeGifsFromDb() {
        observeGifsUseCase.execute(object : SimpleDisposableObserver<List<Gif>>() {
            override fun onNext(t: List<Gif>) {
                gifs = t
                currentGif?.let { gif ->
                    val position = t.indexOf(gif)
                    pair.postValue(Pair(t, position))
                }
            }

            override fun onError(e: Throwable) {
                Log.e(GifListViewModel.TAG, e.localizedMessage ?: e.stackTraceToString())
            }
        })
    }

    override fun onCleared() {
        observeGifsUseCase.clear()
        super.onCleared()
    }
}