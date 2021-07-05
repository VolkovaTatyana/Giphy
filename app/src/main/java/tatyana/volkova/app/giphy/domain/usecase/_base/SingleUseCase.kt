package tatyana.volkova.app.giphy.domain.usecase._base

import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.observers.DisposableSingleObserver

abstract class SingleUseCase<Results, in Params> : BaseReactiveUseCase() {

    abstract fun buildUseCaseSingle(params: Params? = null): Single<Results>

    fun execute(observer: DisposableSingleObserver<Results>, params: Params? = null) = execute(observer, null, params)

    fun execute(observer: DisposableSingleObserver<Results>, transformer: SingleTransformer<Results, Results>? = null, params: Params? = null) {
        var single = buildUseCaseSingle(params)
        if (transformer != null) {
            single = single.compose(transformer)
        }
        addDisposable(single.subscribeWith(observer))
    }
}