package tatyana.volkova.app.giphy.domain.usecase

import android.util.Log
import io.reactivex.Observable
import io.reactivex.subjects.Subject
import tatyana.volkova.app.giphy.data.repository.GifRepository
import tatyana.volkova.app.giphy.domain.model.Request
import tatyana.volkova.app.giphy.domain.usecase._base.ObservableUseCase
import javax.inject.Inject

class GetAndSaveObservableUseCase @Inject constructor(
    private val repository: GifRepository
) : ObservableUseCase<List<Long>, GetAndSaveObservableUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params?): Observable<List<Long>> {
        return params?.let {
            it.subject
                .switchMap { request ->
                    Log.e("GetAndSaveObservableUC", "query = ${request.query}, limit = ${request.limit}, offset = ${request.offset}")
                    repository.getGifs(
                        query = request.query, limit = request.limit, offset = request.offset
                    ).flatMapObservable { list ->
                        repository.addGifsObservable(list)
                    }
                }
        } ?: kotlin.run {
            Observable.error(IllegalArgumentException("Params can't be null"))
        }
    }

    class Params(val subject: Subject<Request>)
}