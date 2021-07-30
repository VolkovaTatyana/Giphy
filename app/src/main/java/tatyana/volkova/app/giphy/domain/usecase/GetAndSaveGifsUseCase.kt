package tatyana.volkova.app.giphy.domain.usecase

import tatyana.volkova.app.giphy.domain.irepository.IRepository
import tatyana.volkova.app.giphy.domain.model.Request
import javax.inject.Inject

class GetAndSaveGifsUseCase @Inject constructor(
    private val repository: IRepository
) {
    suspend fun buildUseCase(params: Params) {
        with(params) {
            val list = repository.getGifs(query = request.query, limit = request.limit, offset = request.offset)
            repository.addGifs(list)
        }
    }

    class Params(val request: Request)
}