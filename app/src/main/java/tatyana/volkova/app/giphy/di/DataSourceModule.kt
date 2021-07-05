package tatyana.volkova.app.giphy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tatyana.volkova.app.giphy.data.idatasource.IRemoteGifDataSource
import tatyana.volkova.app.giphy.data.mapper.IMapper
import tatyana.volkova.app.giphy.data.remote.datasource.RemoteGifDataSource
import tatyana.volkova.app.giphy.data.remote.dto.GifDto
import tatyana.volkova.app.giphy.data.remote.retrofit.ApiService
import tatyana.volkova.app.giphy.domain.common.IExecutor
import tatyana.volkova.app.giphy.domain.model.Gif

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideRemoteGifDataSource(
        apiService: ApiService,
        mapper: IMapper<GifDto, Gif>,
        @Remote executor: IExecutor
    ): IRemoteGifDataSource {
        return RemoteGifDataSource(apiService, mapper, executor)
    }
}