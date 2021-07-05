package tatyana.volkova.app.giphy.data.repository

import io.reactivex.Single
import tatyana.volkova.app.giphy.data.idatasource.IRemoteGifDataSource
import tatyana.volkova.app.giphy.domain.irepository.IGifRepository
import tatyana.volkova.app.giphy.domain.model.Gif
import javax.inject.Inject

class GifRepository @Inject constructor(
    private val remoteGifDataSource: IRemoteGifDataSource
) : IGifRepository {

    override fun getGifs(): Single<List<Gif>> {
        return remoteGifDataSource.getGifs()
    }
}