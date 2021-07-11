package tatyana.volkova.app.giphy.domain.model._base

sealed class ResultEntity<out RequestData>(
    val data: RequestData? = null,
    val error: Throwable? = null,
    val progress: Int? = null
) {
    class SUCCESS<RequestData>(data: RequestData? = null) : ResultEntity<RequestData>(data)
    class LOADING<RequestData>(data: RequestData? = null, progress: Int? = 0)
        : ResultEntity<RequestData>(data, progress = progress)
    class ERROR<RequestData>(error: Throwable? =null, data: RequestData? = null) :
        ResultEntity<RequestData>(data, error)
    class Empty<RequestData>(data: RequestData? = null) : ResultEntity<RequestData>(data)
}