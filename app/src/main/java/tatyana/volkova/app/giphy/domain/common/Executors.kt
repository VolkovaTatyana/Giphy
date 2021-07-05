package tatyana.volkova.app.giphy.domain.common

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface IRemoteExecutor: IExecutor

class RemoteExecutor : IRemoteExecutor {
    override val scheduler = Schedulers.io()
}

interface IMainExecutor: IExecutor

class MainExecutor : IMainExecutor {
    override val scheduler = AndroidSchedulers.mainThread()
}