package tatyana.volkova.app.giphy.data.remote.datasource

import io.reactivex.Single
import tatyana.volkova.app.giphy.data.idatasource.IRemoteGifDataSource
import tatyana.volkova.app.giphy.data.mapper.IMapper
import tatyana.volkova.app.giphy.data.remote.dto.GifDto
import tatyana.volkova.app.giphy.data.remote.retrofit.ApiService
import tatyana.volkova.app.giphy.data.remote.utils.unwrap
import tatyana.volkova.app.giphy.domain.common.IExecutor
import tatyana.volkova.app.giphy.domain.model.Gif
import javax.inject.Inject

class RemoteGifDataSource @Inject constructor(
    private val apiService: ApiService,
    private val mapper: IMapper<GifDto,Gif>,
    private val executor: IExecutor
) : IRemoteGifDataSource {

    override fun getGifs(): Single<List<Gif>> {
        return apiService.getGifs()
            .unwrap()
            .map { it.map (mapper::mapFrom) }
            .subscribeOn(executor.scheduler)
    }
}