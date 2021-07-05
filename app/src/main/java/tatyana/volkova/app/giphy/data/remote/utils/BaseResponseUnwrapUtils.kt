package tatyana.volkova.app.giphy.data.remote.utils

import io.reactivex.Single
import tatyana.volkova.app.giphy.data.remote.dto.BaseResponse

fun Single<BaseResponse>.unwrap() = flatMap {
    if(it.responseData != null){
        Single.just(it.responseData)
    } else {
        Single.error(IllegalStateException(it.meta.msg))
    }
}