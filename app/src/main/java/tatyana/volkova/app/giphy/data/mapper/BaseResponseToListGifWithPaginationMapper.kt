package tatyana.volkova.app.giphy.data.mapper

import tatyana.volkova.app.giphy.data.remote.dto.BaseResponse
import tatyana.volkova.app.giphy.data.remote.dto.GifWithPaginationDto

class BaseResponseToListGifWithPaginationMapper :
    IMapper<BaseResponse, List<GifWithPaginationDto>> {
    override fun mapFrom(from: BaseResponse): List<GifWithPaginationDto> {
        return from.responseData?.map {
            GifWithPaginationDto(
                id = it.id,
                title = it.title,
                url = it.images.original.url,
                totalCount = from.pagination?.totalCount ?: 0,
                count = from.pagination?.count ?: 0,
                offset = from.pagination?.offset ?: 0
            )
        } ?: listOf()
    }
}