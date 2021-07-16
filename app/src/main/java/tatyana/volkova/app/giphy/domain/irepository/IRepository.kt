package tatyana.volkova.app.giphy.domain.irepository

import kotlinx.coroutines.flow.Flow
import tatyana.volkova.app.giphy.domain.model.Gif

interface IRepository {
    suspend fun getGifs(query: String?, limit: Int, offset: Int): List<Gif>
    suspend fun addGifs(gifs: List<Gif>)
    suspend fun deleteGif(id: String)
    fun observeGifs(): Flow<List<Gif>>
}