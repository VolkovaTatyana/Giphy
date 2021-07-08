package tatyana.volkova.app.giphy.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tatyana.volkova.app.giphy.data.device.db.GifDatabase
import tatyana.volkova.app.giphy.data.device.db.dao.GifDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): GifDatabase {
        return Room.databaseBuilder(
            appContext,
            GifDatabase::class.java,
            "gifsDatabase.db"
        ).build()
    }

    @Provides
    fun provideGifDao(database: GifDatabase): GifDao {
        return database.gifDao()
    }
}