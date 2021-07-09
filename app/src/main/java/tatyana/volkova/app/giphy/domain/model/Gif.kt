package tatyana.volkova.app.giphy.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gif(
    val id: String,
    val title: String,
    val url: String,
    val totalCount: Int,
    val count: Int,
    val offset: Int
) : Parcelable