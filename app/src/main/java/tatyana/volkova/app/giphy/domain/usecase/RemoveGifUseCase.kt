package tatyana.volkova.app.giphy.domain.usecase

import io.reactivex.Completable
import tatyana.volkova.app.giphy.data.repository.GifRepository
import tatyana.volkova.app.giphy.domain.usecase._base.CompletableUseCase
import javax.inject.Inject

class RemoveGifUseCase @Inject constructor(
    private val repository: GifRepository
) : CompletableUseCase<RemoveGifUseCase.Params>() {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        return params?.let {
            repository.deleteGif(it.id)
        } ?: kotlin.run {
            Completable.error(IllegalArgumentException("Params can't be null!!!"))
        }
    }

    class Params(val id: String)
}