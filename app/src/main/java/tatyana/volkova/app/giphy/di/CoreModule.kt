package tatyana.volkova.app.giphy.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tatyana.volkova.app.giphy.app.presentation.BaseApplication
import tatyana.volkova.app.giphy.domain.common.IExecutor
import tatyana.volkova.app.giphy.domain.common.MainExecutor
import tatyana.volkova.app.giphy.domain.common.RemoteExecutor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    @Remote
    internal fun provideRemoteExecutor(): IExecutor =
        RemoteExecutor()

    @Provides
    @Singleton
    @Device
    internal fun provideMainExecutor(): IExecutor =
        MainExecutor()

}
