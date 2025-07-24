package uwu.evaware.flora

class Listener<T>(
    val priority: Int = 0,
    val handler: (T) -> Unit
) : Comparable<Listener<T>> {
    override fun compareTo(other: Listener<T>): Int = other.priority.compareTo(priority)
}
