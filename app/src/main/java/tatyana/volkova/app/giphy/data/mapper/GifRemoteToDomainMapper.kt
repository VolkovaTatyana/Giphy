package tatyana.volkova.app.giphy.data.mapper

import tatyana.volkova.app.giphy.data.remote.dto.GifDto
import tatyana.volkova.app.giphy.domain.model.Gif

class GifRemoteToDomainMapper : IMapper<GifDto, Gif> {
    override fun mapFrom(from: GifDto) =
        Gif(id = from.id, title = from.title, url = from.images.original.url)
}