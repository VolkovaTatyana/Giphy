package tatyana.volkova.app.giphy.data.device.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gifs")
class GifEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val url: String,
    val total: Int,
    val count: Int,
    val offset: Int,
    var deleted: Boolean
)