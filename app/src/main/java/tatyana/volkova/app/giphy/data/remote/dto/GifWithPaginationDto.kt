package tatyana.volkova.app.giphy.data.remote.dto

data class GifWithPaginationDto(
    val id: String,
    val title: String,
    val url: String,
    val totalCount: Int,
    val count: Int,
    val offset: Int
)