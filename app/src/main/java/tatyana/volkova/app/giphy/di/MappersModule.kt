package tatyana.volkova.app.giphy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tatyana.volkova.app.giphy.data.mapper.GifRemoteToDomainMapper
import tatyana.volkova.app.giphy.data.mapper.IMapper
import tatyana.volkova.app.giphy.data.remote.dto.GifDto
import tatyana.volkova.app.giphy.domain.model.Gif

@Module
@InstallIn(SingletonComponent::class)
class MappersModule {

    @Provides
    internal fun provideGifRemoteToDomainMapper() : IMapper<GifDto, Gif> {
        return GifRemoteToDomainMapper()
    }
}