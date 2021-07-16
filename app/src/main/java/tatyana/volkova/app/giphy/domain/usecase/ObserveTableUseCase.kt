package tatyana.volkova.app.giphy.domain.usecase

import tatyana.volkova.app.giphy.domain.irepository.IRepository
import javax.inject.Inject

class ObserveTableUseCase @Inject constructor(
    private val repository: IRepository
) {

    fun buildUseCase() =
        repository.observeGifs()
}