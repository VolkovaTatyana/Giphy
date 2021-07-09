package tatyana.volkova.app.giphy.data.remote.datasource

import io.reactivex.Single
import tatyana.volkova.app.giphy.data.idatasource.IRemoteGifDataSource
import tatyana.volkova.app.giphy.data.mapper.BaseResponseToListGifWithPaginationMapper
import tatyana.volkova.app.giphy.data.mapper.IMapper
import tatyana.volkova.app.giphy.data.remote.dto.BaseResponse
import tatyana.volkova.app.giphy.data.remote.dto.GifWithPaginationDto
import tatyana.volkova.app.giphy.data.remote.retrofit.ApiService
import tatyana.volkova.app.giphy.data.remote.utils.unwrap
import tatyana.volkova.app.giphy.domain.common.IExecutor
import tatyana.volkova.app.giphy.domain.model.Gif
import javax.inject.Inject

class RemoteGifDataSource @Inject constructor(
    private val apiService: ApiService,
    private val mapper: IMapper<GifWithPaginationDto, Gif>,
    private val dtoMapper: IMapper<BaseResponse, List<GifWithPaginationDto>>,
    private val executor: IExecutor
) : IRemoteGifDataSource {

    override fun getGifs(limit: Int, offset: Int): Single<List<Gif>> {
        return apiService.getGifs(limit = limit, offset = offset)
            .map { dtoMapper.mapFrom(it) }
            .map { it.map(mapper::mapFrom) }
            .subscribeOn(executor.scheduler)
    }
}