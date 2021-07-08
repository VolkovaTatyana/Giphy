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

    private val gifList = mutableListOf<Gif>()
    private val currentGif = MutableLiveData<Gif>()
    fun getGif(): LiveData<Gif> = currentGif
    fun setGif(gif: Gif) {
        currentGif.value = gif
    }

    init {
        observeGifsFromDb()
    }

    private fun observeGifsFromDb() {
        observeGifsUseCase.execute(object : SimpleDisposableObserver<List<Gif>>() {
            override fun onNext(t: List<Gif>) {
                Log.e(GifListViewModel.TAG, "observeGifsFromDb onNext")
                gifList.addAll(t)
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