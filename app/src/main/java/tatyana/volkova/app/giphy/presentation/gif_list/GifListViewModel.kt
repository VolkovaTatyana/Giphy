package tatyana.volkova.app.giphy.presentation.gif_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tatyana.volkova.app.giphy.domain.model.Gif
import tatyana.volkova.app.giphy.domain.usecase._base.GetGifsUseCase
import tatyana.volkova.app.giphy.domain.usecase._base.observer.SimpleDisposableSingleObserver
import javax.inject.Inject

@HiltViewModel
class GifListViewModel @Inject constructor(
    private val getGifsUseCase: GetGifsUseCase
) : ViewModel() {

    val list = MutableLiveData<List<Gif>>()

    init {
        Log.e(TAG, "GifListViewModel init!!!")
        getGifsUseCase.execute(object : SimpleDisposableSingleObserver<List<Gif>>() {
            override fun onSuccess(result: List<Gif>) {
                Log.e(TAG, result.toString())
                list.postValue(result)
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, e.localizedMessage ?: e.stackTraceToString())
            }
        })
    }

    override fun onCleared() {
        getGifsUseCase.clear()
        super.onCleared()
    }

    companion object{
        const val TAG = "GifListViewModel"
    }
}