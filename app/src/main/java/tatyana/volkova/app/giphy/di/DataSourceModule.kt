package tatyana.volkova.app.giphy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tatyana.volkova.app.giphy.data.device.datasource.DeviceGifDataSource
import tatyana.volkova.app.giphy.data.device.db.dao.GifDao
import tatyana.volkova.app.giphy.data.device.db.entity.GifEntity
import tatyana.volkova.app.giphy.data.idatasource.IDeviceGifDataSource
import tatyana.volkova.app.giphy.data.idatasource.IRemoteGifDataSource
import tatyana.volkova.app.giphy.data.mapper.IMapper
import tatyana.volkova.app.giphy.data.remote.datasource.RemoteGifDataSource
import tatyana.volkova.app.giphy.data.remote.dto.BaseResponse
import tatyana.volkova.app.giphy.data.remote.dto.GifWithPaginationDto
import tatyana.volkova.app.giphy.data.remote.retrofit.ApiService
import tatyana.volkova.app.giphy.domain.common.IExecutor
import tatyana.volkova.app.giphy.domain.model.Gif

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideRemoteGifDataSource(
        apiService: ApiService,
        mapper: IMapper<GifWithPaginationDto, Gif>,
        dtoMapper: IMapper<BaseResponse, List<GifWithPaginationDto>>,
        @Remote executor: IExecutor
    ): IRemoteGifDataSource {
        return RemoteGifDataSource(apiService, mapper, dtoMapper, executor)
    }

    @Provides
    fun provideDeviceGifDataSource(
        dao: GifDao,
        toDomainMapper: IMapper<GifEntity, Gif>,
        toDeviceMapper: IMapper<Gif, GifEntity>,
        @Remote executor: IExecutor
    ): IDeviceGifDataSource {
        return DeviceGifDataSource(dao, toDomainMapper, toDeviceMapper, executor)
    }
}