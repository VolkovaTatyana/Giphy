package tatyana.volkova.app.giphy.domain.irepository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import tatyana.volkova.app.giphy.domain.model.Gif

interface IGifRepository {

    fun getGifs(query: String?, limit: Int, offset: Int): Single<List<Gif>>
    fun addGifsObservable(gifs: List<Gif>): Observable<List<Long>>
    fun observeGifs(): Observable<List<Gif>>
    fun deleteGif(id: String): Completable
}