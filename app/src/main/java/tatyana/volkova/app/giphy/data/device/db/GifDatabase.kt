package tatyana.volkova.app.giphy.data.device.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tatyana.volkova.app.giphy.data.device.db.dao.GifDao
import tatyana.volkova.app.giphy.data.device.db.entity.GifEntity

@Database(entities = [GifEntity::class], version = 1, exportSchema = false)
abstract class GifDatabase : RoomDatabase() {
    abstract fun gifDao(): GifDao
}