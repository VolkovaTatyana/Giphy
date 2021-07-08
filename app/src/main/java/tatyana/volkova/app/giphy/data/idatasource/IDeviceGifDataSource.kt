package tatyana.volkova.app.giphy.data.idatasource

import io.reactivex.Completable
import io.reactivex.Observable
import tatyana.volkova.app.giphy.domain.model.Gif

interface IDeviceGifDataSource {
    fun addGifs(gifs: List<Gif>): Completable
    fun observeGifs(): Observable<List<Gif>>
    fun deleteGif(id: String): Completable
}