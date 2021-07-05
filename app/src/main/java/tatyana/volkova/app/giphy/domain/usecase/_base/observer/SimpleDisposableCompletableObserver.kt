package tatyana.volkova.app.giphy.domain.usecase._base.observer

import io.reactivex.observers.DisposableCompletableObserver

abstract class SimpleDisposableCompletableObserver : DisposableCompletableObserver() {

    override fun onComplete() {
        // Override to handle result
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }
}