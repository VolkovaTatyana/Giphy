package tatyana.volkova.app.giphy.app.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tatyana.volkova.app.giphy.domain.model.Gif
import tatyana.volkova.app.giphy.domain.usecase._base.GetGifsUseCase
import tatyana.volkova.app.giphy.domain.usecase._base.observer.SimpleDisposableSingleObserver
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(
    private val getGifsUseCase: GetGifsUseCase
) : ViewModel() {

    val list = MutableLiveData<List<Gif>>()

    init {
        Timber.e("MainViewModel")
        getGifsUseCase.execute(object : SimpleDisposableSingleObserver<List<Gif>>() {
            override fun onSuccess(result: List<Gif>) {
                Timber.e(result.toString())
                list.postValue(result)
            }

            override fun onError(e: Throwable) {
                Timber.e(e.localizedMessage)
            }
        })
    }

}