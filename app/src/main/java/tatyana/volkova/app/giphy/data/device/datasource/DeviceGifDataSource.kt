package tatyana.volkova.app.giphy.data.device.datasource

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import tatyana.volkova.app.giphy.data.device.db.dao.GifDao
import tatyana.volkova.app.giphy.data.device.db.entity.GifEntity
import tatyana.volkova.app.giphy.data.idatasource.IDeviceGifDataSource
import tatyana.volkova.app.giphy.data.mapper.IMapper
import tatyana.volkova.app.giphy.domain.common.IExecutor
import tatyana.volkova.app.giphy.domain.model.Gif
import javax.inject.Inject

class DeviceGifDataSource @Inject constructor(
    private val dao: GifDao,
    private val toDomainMapper: IMapper<GifEntity, Gif>,
    private val toDeviceMapper: IMapper<Gif, GifEntity>,
    private val executor: IExecutor
) : IDeviceGifDataSource {

    override fun addGifs(gifs: List<Gif>): Completable {
        return dao.saveGifs(gifs.map(toDeviceMapper::mapFrom))
            .subscribeOn(executor.scheduler)
    }

    override fun addGifsSingle(gifs: List<Gif>): Single<List<Long>> {
        return dao.saveGifsSingle(gifs.map(toDeviceMapper::mapFrom))
            .subscribeOn(executor.scheduler)
    }

    override fun observeGifs(): Observable<List<Gif>> {
        return dao.getAllNotDeletedObservable()
            .map { it.map(toDomainMapper::mapFrom) }
            .subscribeOn(executor.scheduler)
    }

    override fun deleteGif(id: String): Completable {
        return dao.markEntityAsDeleted(id).subscribeOn(executor.scheduler)
    }
}