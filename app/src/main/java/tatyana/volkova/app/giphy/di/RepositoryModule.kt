package tatyana.volkova.app.giphy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tatyana.volkova.app.giphy.data.device.datasource.DeviceGifDataSource
import tatyana.volkova.app.giphy.data.idatasource.IDeviceDataSource
import tatyana.volkova.app.giphy.data.idatasource.IDeviceGifDataSource
import tatyana.volkova.app.giphy.data.idatasource.IRemoteDataSource
import tatyana.volkova.app.giphy.data.idatasource.IRemoteGifDataSource
import tatyana.volkova.app.giphy.data.repository.GifRepository
import tatyana.volkova.app.giphy.data.repository.Repository
import tatyana.volkova.app.giphy.domain.irepository.IGifRepository
import tatyana.volkova.app.giphy.domain.irepository.IRepository

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    internal fun provideGifRepository(
        remoteGifDataSource: IRemoteGifDataSource,
        deviceGifDataSource: IDeviceGifDataSource
    ): IGifRepository {
        return GifRepository(remoteGifDataSource, deviceGifDataSource)
    }

    @Provides
    internal fun provideRepository(
        remoteDataSource: IRemoteDataSource,
        deviceDataSource: IDeviceDataSource
    ): IRepository {
        return Repository(remoteDataSource, deviceDataSource)
    }
}