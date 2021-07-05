package tatyana.volkova.app.giphy.app.presentation.gif_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tatyana.volkova.app.giphy.domain.usecase._base.GetGifsUseCase
import javax.inject.Inject

@HiltViewModel
class GifListViewModel @Inject constructor(
    private val getGifsUseCase: GetGifsUseCase
) : ViewModel() {
}