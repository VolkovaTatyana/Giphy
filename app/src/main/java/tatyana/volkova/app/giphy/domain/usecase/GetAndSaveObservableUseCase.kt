package tatyana.volkova.app.giphy.domain.usecase

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import tatyana.volkova.app.giphy.data.repository.GifRepository
import tatyana.volkova.app.giphy.domain.model.Request
import tatyana.volkova.app.giphy.domain.model._base.ResultEntity
import tatyana.volkova.app.giphy.domain.usecase._base.ObservableUseCase
import javax.inject.Inject

class GetAndSaveObservableUseCase @Inject constructor(
    private val repository: GifRepository
) : ObservableUseCase<ResultEntity<List<Long>>, GetAndSaveObservableUseCase.Params>() {

    private val errorSubject = PublishSubject.create<ResultEntity<List<Long>>>()

    override fun buildUseCaseObservable(params: Params?): Observable<ResultEntity<List<Long>>>  =
        params?.let {
            Observable.merge(create(it), errorSubject)
        } ?: kotlin.run {
            Observable.error(IllegalArgumentException("Params can't be null"))
        }

    private fun create(params: Params): Observable<ResultEntity<List<Long>>> {
        return params.subject
            .distinctUntilChanged()
            .debounce(500, java.util.concurrent.TimeUnit.MILLISECONDS)
            .switchMap { request ->
                observableRequest(request)
                    .startWith(Observable.just(ResultEntity.LOADING()))
            }.doOnError {
                errorSubject.onNext(ResultEntity.ERROR(it))
            }.onErrorResumeNext(
                Observable.defer { create(params) }
            )
    }

    private fun observableRequest(request: Request): Observable<ResultEntity<List<Long>>> =
        repository.getGifs(
            query = request.query, limit = request.limit, offset = request.offset
        ).flatMapObservable { list ->
            repository.addGifsObservable(list)
                .map { ResultEntity.SUCCESS(it) }
        }

    class Params(val subject: Subject<Request>)
}