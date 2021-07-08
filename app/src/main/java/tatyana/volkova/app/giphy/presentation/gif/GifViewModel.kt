package tatyana.volkova.app.giphy.presentation.gif

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tatyana.volkova.app.giphy.domain.model.Gif
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(

) : ViewModel() {

    private val currentGif = MutableLiveData<Gif>()
    fun getGif(): LiveData<Gif> = currentGif
    fun setGif(gif: Gif) {
        currentGif.value = gif
    }
}