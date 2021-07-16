package tatyana.volkova.app.giphy.data.device.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tatyana.volkova.app.giphy.data.device.db.dao.GifDao
import tatyana.volkova.app.giphy.data.device.db.entity.GifEntity
import tatyana.volkova.app.giphy.data.idatasource.IDeviceDataSource
import tatyana.volkova.app.giphy.data.mapper.IMapper
import tatyana.volkova.app.giphy.domain.model.Gif
import javax.inject.Inject

class DeviceDataSource @Inject constructor(
    private val dao: GifDao,
    private val toDomainMapper: IMapper<GifEntity, Gif>,
    private val toDeviceMapper: IMapper<Gif, GifEntity>
) : IDeviceDataSource {

    override suspend fun addGifs(gifs: List<Gif>) =
        dao.saveGifs(gifs.map(toDeviceMapper::mapFrom))

    override suspend fun deleteGif(id: String) =
        dao.markEntityDeleted(id)

    override fun observeGifs(): Flow<List<Gif>> =
        dao.getAllNotDeleted().map {
            it.map(toDomainMapper::mapFrom)
        }
}