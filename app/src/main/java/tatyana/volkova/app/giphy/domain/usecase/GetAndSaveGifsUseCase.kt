package tatyana.volkova.app.giphy.domain.usecase

import io.reactivex.Completable
import io.reactivex.subjects.Subject
import tatyana.volkova.app.giphy.data.repository.GifRepository
import tatyana.volkova.app.giphy.domain.model.Gif
import tatyana.volkova.app.giphy.domain.model.Request
import tatyana.volkova.app.giphy.domain.usecase._base.CompletableUseCase
import java.lang.IllegalArgumentException
import javax.inject.Inject

class GetAndSaveGifsUseCase @Inject constructor(
    private val repository: GifRepository
) : CompletableUseCase<GetAndSaveGifsUseCase.Params>() {

    override fun buildUseCaseCompletable(params: Params?): Completable {
        return params?.let {
            repository.getGifs(query = it.query, limit = it.limit, offset = it.offset)
                .flatMapCompletable { list ->
                    repository.addGifs(list)
                }
        } ?: kotlin.run {
            Completable.error(IllegalArgumentException("Params can't be null!!!"))
        }
    }

    class Params(val limit: Int = 20, val offset: Int = 0, val query: String? = null)
}