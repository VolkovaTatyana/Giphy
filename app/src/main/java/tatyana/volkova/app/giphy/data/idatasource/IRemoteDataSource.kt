package tatyana.volkova.app.giphy.data.idatasource

import tatyana.volkova.app.giphy.domain.model.Gif

interface IRemoteDataSource {
    suspend fun getGifs(query: String?, limit: Int, offset: Int): List<Gif>
}