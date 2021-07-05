package tatyana.volkova.app.giphy.domain.common

import io.reactivex.Scheduler

interface IExecutor {
    val scheduler: Scheduler
}