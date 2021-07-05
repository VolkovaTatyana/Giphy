package tatyana.volkova.app.giphy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tatyana.volkova.app.giphy.data.idatasource.IRemoteGifDataSource
import tatyana.volkova.app.giphy.data.repository.GifRepository
import tatyana.volkova.app.giphy.domain.irepository.IGifRepository

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    internal fun provideGifRepository(
        remoteGifDataSource: IRemoteGifDataSource
    ): IGifRepository {
        return GifRepository(remoteGifDataSource)
    }
}