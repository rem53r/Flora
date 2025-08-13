package uwu.evaware.flora

import java.util.concurrent.atomic.AtomicLong

class Listener<T>(
    val priority: Int = 0,
    val handler: suspend (T) -> Boolean
) : Comparable<Listener<T>> {
    companion object { private val counter = AtomicLong(0) }

    private val id = counter.getAndIncrement()

    override fun compareTo(other: Listener<T>): Int {
        val prioCompare = other.priority.compareTo(priority)
        return if (prioCompare != 0) prioCompare else id.compareTo(other.id)
    }
}