package tatyana.volkova.app.giphy.domain.usecase._base.observer

import io.reactivex.observers.DisposableObserver

abstract class SimpleDisposableObserver<T> : DisposableObserver<T>() {

    override fun onComplete() {
        // Override to handle result
    }

    override fun onNext(result: T) {
        // Override to handle result
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }
}