package tatyana.volkova.app.giphy.domain.irepository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import tatyana.volkova.app.giphy.domain.model.Gif

interface IGifRepository {

    fun getGifs(limit: Int, offset: Int): Single<List<Gif>>
    fun addGifs(gifs: List<Gif>): Completable
    fun observeGifs(): Observable<List<Gif>>
    fun deleteGif(id: String): Completable
}