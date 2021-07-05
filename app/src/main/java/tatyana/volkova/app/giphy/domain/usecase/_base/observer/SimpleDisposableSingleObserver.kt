package tatyana.volkova.app.giphy.domain.usecase._base.observer

import io.reactivex.observers.DisposableSingleObserver

abstract class SimpleDisposableSingleObserver<T> : DisposableSingleObserver<T>() {

    override fun onSuccess(result: T) {
        // Override to handle result
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }
}