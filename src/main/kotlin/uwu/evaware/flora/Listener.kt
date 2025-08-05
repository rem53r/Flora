package uwu.evaware.flora

class Listener<T>(
    val priority: Int = 0,
    val handler: (T) -> Unit
) : Comparable<Listener<T>> {
    private val id = System.identityHashCode(handler)

    override fun compareTo(other: Listener<T>): Int {
        val prioCompare = other.priority.compareTo(priority)
        return if (prioCompare != 0) prioCompare else id.compareTo(other.id)
    }
}