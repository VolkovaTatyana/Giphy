package tatyana.volkova.app.giphy.domain.usecase._base

import io.reactivex.Completable
import io.reactivex.CompletableTransformer
import io.reactivex.observers.DisposableCompletableObserver
import tatyana.volkova.app.giphy.domain.usecase._base.observer.SimpleDisposableCompletableObserver

abstract class CompletableUseCase<in Params> : BaseReactiveUseCase() {

    abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    fun execute(observer: DisposableCompletableObserver = object : SimpleDisposableCompletableObserver() {}, params: Params? = null) = execute(observer, null, params)

    fun execute(observer: DisposableCompletableObserver, transformer: CompletableTransformer? = null, params: Params? = null) {
        var completable = buildUseCaseCompletable(params)
        if (transformer != null) {
            completable = completable.compose(transformer)
        }
        addDisposable(completable.subscribeWith(observer))
    }
}