package tatyana.volkova.app.giphy.data.idatasource

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import tatyana.volkova.app.giphy.domain.model.Gif

interface IDeviceGifDataSource {
    fun addGifsSingle(gifs: List<Gif>): Single<List<Long>>
    fun observeGifs(): Observable<List<Gif>>
    fun deleteGif(id: String): Completable
}