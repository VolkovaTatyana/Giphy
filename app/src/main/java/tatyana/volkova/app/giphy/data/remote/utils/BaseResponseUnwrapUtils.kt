package tatyana.volkova.app.giphy.data.remote.utils

import io.reactivex.Single
import tatyana.volkova.app.giphy.data.mapper.BaseResponseToListGifWithPaginationMapper
import tatyana.volkova.app.giphy.data.remote.dto.BaseResponse
import tatyana.volkova.app.giphy.data.remote.dto.GifWithPaginationDto

fun Single<BaseResponse>.unwrap() = flatMap {
    if (it.responseData != null) {
        Single.just(BaseResponseToListGifWithPaginationMapper().mapFrom(it))
    } else {
        Single.error(IllegalStateException(it.meta.msg))
    }
}