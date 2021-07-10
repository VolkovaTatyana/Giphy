package tatyana.volkova.app.giphy.domain.model

data class Request(
    val limit: Int = 20,
    val offset: Int = 0,
    val query: String? = ""
)