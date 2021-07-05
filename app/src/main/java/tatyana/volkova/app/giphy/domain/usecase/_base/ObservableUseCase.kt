package tatyana.volkova.app.giphy.domain.usecase._base

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<Results, in Params> : BaseReactiveUseCase() {

    abstract fun buildUseCaseObservable(params: Params? = null): Observable<Results>

    fun execute(observer: DisposableObserver<Results>, params: Params? = null) = execute(observer, null, params)

    fun execute(observer: DisposableObserver<Results>, transformer: ObservableTransformer<Results, Results>? = null, params: Params? = null) {
        var observable = buildUseCaseObservable(params)
        if (transformer != null) {
            observable = observable.compose(transformer)
        }
        addDisposable(observable.subscribeWith(observer))
    }
}