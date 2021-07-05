package tatyana.volkova.app.giphy.domain.usecase._base

import io.reactivex.Single
import tatyana.volkova.app.giphy.data.repository.GifRepository
import tatyana.volkova.app.giphy.domain.model.Gif
import javax.inject.Inject

class GetGifsUseCase @Inject constructor(
    private val repository: GifRepository
) : SingleUseCase<List<Gif>,GetGifsUseCase.Params>(){

    override fun buildUseCaseSingle(params: Params?): Single<List<Gif>> {
        return repository.getGifs()
    }

    class Params
}