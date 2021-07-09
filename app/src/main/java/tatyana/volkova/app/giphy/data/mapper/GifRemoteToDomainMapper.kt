package tatyana.volkova.app.giphy.data.mapper

import tatyana.volkova.app.giphy.data.remote.dto.GifWithPaginationDto
import tatyana.volkova.app.giphy.domain.model.Gif

class GifRemoteToDomainMapper : IMapper<GifWithPaginationDto, Gif> {
    override fun mapFrom(from: GifWithPaginationDto) =
        Gif(id = from.id,
            title = from.title,
            url = from.url,
            totalCount = from.totalCount,
            count = from.count,
            offset = from.offset
        )
}