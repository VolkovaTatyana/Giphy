package tatyana.volkova.app.giphy.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import tatyana.volkova.app.giphy.data.idatasource.IDeviceGifDataSource
import tatyana.volkova.app.giphy.data.idatasource.IRemoteGifDataSource
import tatyana.volkova.app.giphy.domain.irepository.IGifRepository
import tatyana.volkova.app.giphy.domain.model.Gif
import javax.inject.Inject

class GifRepository @Inject constructor(
    private val remoteGifDataSource: IRemoteGifDataSource,
    private val deviceGifDataSource: IDeviceGifDataSource
) : IGifRepository {

    override fun getGifs(query: String?, limit: Int, offset: Int): Single<List<Gif>> {
        return remoteGifDataSource.getGifs(query, limit, offset)
    }

    override fun addGifs(gifs: List<Gif>): Completable {
        return deviceGifDataSource.addGifs(gifs)
    }

    override fun observeGifs(): Observable<List<Gif>> {
        return deviceGifDataSource.observeGifs()
    }

    override fun deleteGif(id: String): Completable {
        return deviceGifDataSource.deleteGif(id)
    }
}