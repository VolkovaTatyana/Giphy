package tatyana.volkova.app.giphy.domain.usecase

import io.reactivex.Completable
import tatyana.volkova.app.giphy.data.repository.GifRepository
import tatyana.volkova.app.giphy.domain.model.Gif
import tatyana.volkova.app.giphy.domain.usecase._base.CompletableUseCase
import java.lang.IllegalArgumentException
import javax.inject.Inject

class GetAndSaveGifsUseCase @Inject constructor(
    private val repository: GifRepository
) : CompletableUseCase<GetAndSaveGifsUseCase.Params>() {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        return repository.getGifs()
            .flatMapCompletable {
                repository.addGifs(it)
            }

    }

    class Params
}