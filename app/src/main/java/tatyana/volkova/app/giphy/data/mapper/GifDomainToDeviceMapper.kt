package tatyana.volkova.app.giphy.data.mapper

import tatyana.volkova.app.giphy.data.device.db.entity.GifEntity
import tatyana.volkova.app.giphy.domain.model.Gif

class GifDomainToDeviceMapper : IMapper<Gif, GifEntity> {
    override fun mapFrom(from: Gif) = GifEntity(
        id = from.id,
        title = from.title,
        url = from.url,
        total = from.totalCount,
        count = from.count,
        offset = from.offset,
        deleted = false
    )
}