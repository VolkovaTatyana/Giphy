package tatyana.volkova.app.giphy.domain.model

import java.util.ArrayList

class PaginationList<T>(
    private val wrappedList: MutableList<T>,
    private val maxSize: Int = wrappedList.size,
    private val page: Int = 0,
    private val hasMore: Boolean = false
) : MutableList<T> {

    fun getMaxSize() = maxSize

    fun getNumberOfPage() = page

    fun hasMore() = hasMore

    override fun contains(element: T) = wrappedList.contains(element)

    override fun containsAll(elements: Collection<T>) = wrappedList.containsAll(elements)

    override fun indexOf(element: T) = wrappedList.indexOf(element)

    override fun lastIndexOf(element: T) = wrappedList.lastIndexOf(element)

    override fun remove(element: T) = wrappedList.remove(element)

    override fun removeAll(elements: Collection<T>) = wrappedList.removeAll(elements)

    override fun removeAt(index: Int) = wrappedList.removeAt(index)

    override fun retainAll(elements: Collection<T>) = wrappedList.retainAll(elements)

    override fun isEmpty() = wrappedList.isEmpty()

    override fun iterator() = wrappedList.iterator()

    override fun add(element: T): Boolean {
        require(wrappedList.size + 1 <= maxSize) { "No more than maxsize items can be added to this pagination" }
        return wrappedList.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        require(wrappedList.size + elements.size <= maxSize) { "No more than maxsize items can be added to this pagination" }
        return wrappedList.addAll(elements)
    }

    override fun addAll(
        index: Int,
        elements: Collection<T>
    ): Boolean {
        require(wrappedList.size + elements.size <= maxSize) { "No more than maxsize items can be added to this pagination" }
        return wrappedList.addAll(index, elements)
    }

    override fun clear() {
        wrappedList.clear()
    }

    override fun equals(other: Any?) = wrappedList == other

    override fun hashCode() = wrappedList.hashCode()

    override fun get(index: Int) = wrappedList[index]

    override fun set(index: Int, element: T) = wrappedList.set(index, element)

    override fun add(index: Int, element: T) {
        require(wrappedList.size + 1 <= maxSize) { "No more than maxsize items can be added to this pagination" }
        wrappedList.add(index, element)
    }

    override fun listIterator(): MutableListIterator<T> = PaginationListIterator(this, wrappedList.listIterator())

    override fun listIterator(index: Int): MutableListIterator<T> = PaginationListIterator(this, wrappedList.listIterator(index))

    override fun subList(fromIndex: Int, toIndex: Int) = wrappedList.subList(fromIndex, toIndex)

    override val size: Int
        get() = wrappedList.size

    private class PaginationListIterator<T>(
        private val list: PaginationList<T>,
        private val iterator: MutableListIterator<T>
    ) : MutableListIterator<T> {
        override fun hasNext() = iterator.hasNext()

        override fun next() = iterator.next()

        override fun hasPrevious() = iterator.hasPrevious()

        override fun previous() = iterator.previous()

        override fun nextIndex() = iterator.nextIndex()

        override fun previousIndex() = iterator.previousIndex()

        override fun remove() {
            iterator.remove()
        }

        override fun set(element: T) {
            iterator.set(element)
        }

        override fun add(element: T) {
            require(list.maxSize + 1 <= 0) { "No more than maxsize items can be added to this pagination" }
            iterator.add(element)
        }
    }
}

fun <T> Collection<T>.toPaginationList() = PaginationList(ArrayList(this))
fun <T> Collection<T>.toPaginationListAsList(maxSize: Int, page: Int, hasMore: Boolean) = PaginationList(
    ArrayList(this), maxSize, page, hasMore) as List<T>