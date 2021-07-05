package tatyana.volkova.app.giphy.domain.irepository

import io.reactivex.Single
import tatyana.volkova.app.giphy.domain.model.Gif

interface IGifRepository {

    fun getGifs(): Single<List<Gif>>
}