package tatyana.volkova.app.giphy.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tatyana.volkova.app.giphy.data.device.db.entity.GifEntity
import tatyana.volkova.app.giphy.data.mapper.*
import tatyana.volkova.app.giphy.data.remote.dto.BaseResponse
import tatyana.volkova.app.giphy.data.remote.dto.GifDto
import tatyana.volkova.app.giphy.data.remote.dto.GifWithPaginationDto
import tatyana.volkova.app.giphy.domain.model.Gif

@Module
@InstallIn(SingletonComponent::class)
class MappersModule {

    @Provides
    internal fun provideBaseResponseToListGifWithPaginationMapper(): IMapper<BaseResponse, List<GifWithPaginationDto>> {
        return BaseResponseToListGifWithPaginationMapper()
    }

    @Provides
    internal fun provideGifRemoteToDomainMapper(): IMapper<GifWithPaginationDto, Gif> {
        return GifRemoteToDomainMapper()
    }

    @Provides
    internal fun provideGifDomainToDeviceMapper(): IMapper<Gif, GifEntity> {
        return GifDomainToDeviceMapper()
    }

    @Provides
    internal fun provideGifDeviceToDomainMapper(): IMapper<GifEntity, Gif> {
        return GifDeviceToDomainMapper()
    }
}