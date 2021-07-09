package tatyana.volkova.app.giphy.presentation.pagination

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.util.concurrent.atomic.AtomicReference

typealias PagingAdapter = () -> Unit

class PagingListener(private val pageSize: Int = 10) : RecyclerView.OnScrollListener() {

    private val isLoading = AtomicReference(false)

    private val isRegistered = AtomicReference(false)

    private val listCallback =
        PagingListCallback(isLoading)

    private val pageListener = AtomicReference<PagingAdapter>()
    private val pageScrollListener = AtomicReference<(Int, Int) -> Unit>()

    private val currentScroll = AtomicReference(0)

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(view, dx, dy)
        pageScrollListener.get()?.invoke(dx, dy)
        if (isRegistered.getAndSet(true).not()) view.adapter?.registerAdapterDataObserver(
            listCallback
        )

        if (currentScroll.getAndSet(dx) < dx) return

        if (isLoading.get() || view.isComputingLayout == true) return

        val layoutManager = view.layoutManager ?: return

        val itemCount = layoutManager.itemCount
        val lastVisiblePosition =
            (layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition()
                ?: (layoutManager as? GridLayoutManager)?.findLastVisibleItemPosition()
                ?: (layoutManager as? StaggeredGridLayoutManager)?.findLastVisibleItemPositions(
                    intArrayOf(0, 1)
                )?.get(0) ?: throw IllegalArgumentException("Paging not supported for ${view.layoutManager}")
        val offset = lastVisiblePosition.plus(layoutManager.offset())

        if (offset > itemCount && itemCount - pageSize >= 0) {
            onNext()
        }
    }

    private fun RecyclerView.LayoutManager.offset(): Int {
        return (this as? GridLayoutManager)?.spanCount?.times(2) ?: 5
    }

    private fun onNext() {
        isLoading.set(true)
        pageListener.get()?.invoke()
    }

    fun attach(pageListener: PagingAdapter, scrollPositionListener: ((Int, Int) -> Unit)? = null): PagingListener {
        this.pageListener.set(pageListener)
        scrollPositionListener?.let { this.pageScrollListener.set(it) }
        return this
    }
}


fun RecyclerView.paging(scrollPositionListener: ((Int, Int) -> Unit)? = null, listener: PagingAdapter) =
    addOnAttachStateChangeListener(PagingAttacher(this, listener, scrollPositionListener))

private class PagingAttacher(list: RecyclerView, listener: PagingAdapter, scrollPositionListener: ((Int, Int) -> Unit)? = null) :
    View.OnAttachStateChangeListener {

    private val list = AtomicReference(list)

    private val pagination = AtomicReference(PagingListener().attach(listener, scrollPositionListener))

    override fun onViewDetachedFromWindow(p0: View?) {
        list.get()?.removeOnScrollListener(pagination.get())
        list.get()?.removeOnAttachStateChangeListener(this)
    }

    override fun onViewAttachedToWindow(p0: View?) {
        list.get()?.addOnScrollListener(pagination.get())
    }
}