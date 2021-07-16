package tatyana.volkova.app.giphy.data.idatasource

import kotlinx.coroutines.flow.Flow
import tatyana.volkova.app.giphy.domain.model.Gif

interface IDeviceDataSource {
    suspend fun addGifs(gifs: List<Gif>)
    suspend fun deleteGif(id: String)
    fun observeGifs(): Flow<List<Gif>>
}