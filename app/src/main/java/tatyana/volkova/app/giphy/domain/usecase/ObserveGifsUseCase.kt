package tatyana.volkova.app.giphy.domain.usecase

import io.reactivex.Observable
import tatyana.volkova.app.giphy.data.repository.GifRepository
import tatyana.volkova.app.giphy.domain.model.Gif
import tatyana.volkova.app.giphy.domain.usecase._base.ObservableUseCase
import javax.inject.Inject

class ObserveGifsUseCase @Inject constructor(
    private val repository: GifRepository
) : ObservableUseCase<List<Gif>, ObserveGifsUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params?): Observable<List<Gif>> {
        return repository.observeGifs()
    }

    class Params
}