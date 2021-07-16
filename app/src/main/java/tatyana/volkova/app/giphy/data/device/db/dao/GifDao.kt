package tatyana.volkova.app.giphy.data.device.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import tatyana.volkova.app.giphy.data.device.db.entity.GifEntity

@Dao
interface GifDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveGifs(gifs: List<GifEntity>)

    @Query("SELECT * FROM gifs WHERE deleted = 0")
    fun getAllNotDeleted(): Flow<List<GifEntity>>

    @Query("UPDATE gifs SET deleted = 1 WHERE id = :id")
    suspend fun markEntityDeleted(id: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveGifsSingle(gifs: List<GifEntity>): Single<List<Long>>

    @Query("SELECT * FROM gifs WHERE deleted = 0")
    fun getAllNotDeletedObservable(): Observable<List<GifEntity>>

    @Query("UPDATE gifs SET deleted = 1 WHERE id = :id")
    fun markEntityAsDeleted(id: String): Completable
}