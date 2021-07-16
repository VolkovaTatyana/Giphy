package tatyana.volkova.app.giphy.data.remote.datasource

import tatyana.volkova.app.giphy.data.idatasource.IRemoteDataSource
import tatyana.volkova.app.giphy.data.mapper.IMapper
import tatyana.volkova.app.giphy.data.remote.dto.BaseResponse
import tatyana.volkova.app.giphy.data.remote.dto.GifWithPaginationDto
import tatyana.volkova.app.giphy.data.remote.retrofit.ApiService
import tatyana.volkova.app.giphy.domain.model.Gif
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val mapper: IMapper<GifWithPaginationDto, Gif>,
    private val dtoMapper: IMapper<BaseResponse, List<GifWithPaginationDto>>
) : IRemoteDataSource {

    override suspend fun getGifs(query: String?, limit: Int, offset: Int): List<Gif> {
        val endPoint = if (query.isNullOrEmpty()) "trending" else "search"
        return apiService.getOrFindGifs(endPoint = endPoint, query = query, limit = limit, offset = offset)
            .let {
                dtoMapper.mapFrom(it)
                    .map(mapper::mapFrom)
            }
    }
}