package tatyana.volkova.app.giphy.data.mapper

import tatyana.volkova.app.giphy.data.device.db.entity.GifEntity
import tatyana.volkova.app.giphy.domain.model.Gif

class GifDeviceToDomainMapper : IMapper<GifEntity, Gif> {
    override fun mapFrom(from: GifEntity) = Gif(
        id = from.id,
        title = from.title,
        url = from.url,
        totalCount = from.total,
        count = from.count,
        offset = from.offset
    )
}