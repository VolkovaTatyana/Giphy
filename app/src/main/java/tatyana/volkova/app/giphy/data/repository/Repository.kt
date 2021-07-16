package tatyana.volkova.app.giphy.data.repository

import kotlinx.coroutines.flow.Flow
import tatyana.volkova.app.giphy.data.idatasource.IDeviceDataSource
import tatyana.volkova.app.giphy.data.idatasource.IRemoteDataSource
import tatyana.volkova.app.giphy.domain.irepository.IRepository
import tatyana.volkova.app.giphy.domain.model.Gif
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: IRemoteDataSource,
    private val deviceDataSource: IDeviceDataSource
) : IRepository {

    override suspend fun getGifs(query: String?, limit: Int, offset: Int): List<Gif> =
        remoteDataSource.getGifs(query = query, limit = limit, offset = offset)

    override suspend fun addGifs(gifs: List<Gif>) =
        deviceDataSource.addGifs(gifs)

    override suspend fun deleteGif(id: String) =
        deviceDataSource.deleteGif(id)

    override fun observeGifs(): Flow<List<Gif>> =
        deviceDataSource.observeGifs()
}