package tatyana.volkova.app.giphy.data.idatasource

import io.reactivex.Single
import tatyana.volkova.app.giphy.domain.model.Gif

interface IRemoteGifDataSource {
    fun getGifs(query: String?, limit: Int, offset: Int): Single<List<Gif>>
}