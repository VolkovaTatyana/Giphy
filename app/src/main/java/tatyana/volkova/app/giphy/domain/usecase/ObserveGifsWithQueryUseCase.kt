package tatyana.volkova.app.giphy.domain.usecase

import io.reactivex.Observable
import io.reactivex.subjects.Subject
import tatyana.volkova.app.giphy.data.repository.GifRepository
import tatyana.volkova.app.giphy.domain.model.Gif
import tatyana.volkova.app.giphy.domain.usecase._base.ObservableUseCase
import javax.inject.Inject

class ObserveGifsWithQueryUseCase @Inject constructor(
    private val repository: GifRepository
) : ObservableUseCase<List<Gif>, ObserveGifsWithQueryUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params?): Observable<List<Gif>> {
        return params?.let { params ->
            params.subject
                .switchMap { request ->
                    repository.observeGifs()
                        .map { list ->
                            if (request.query.isNullOrBlank()) {
                                list
                            } else {
                                list.filter { it.title.contains(request.query) }
                            }
                        }
                }
        } ?: kotlin.run {
            Observable.error { IllegalArgumentException("Params can't be null!!!") }
        }

    }

    class Params(val subject: Subject<Request>)

    data class Request(val query: String? = "")
}