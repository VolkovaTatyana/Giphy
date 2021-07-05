package tatyana.volkova.app.giphy.domain.usecase._base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseReactiveUseCase {

    private val disposables = CompositeDisposable()

    open fun clear() {
        disposables.clear()
    }

    open fun dispose() {
        disposables.dispose()
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}