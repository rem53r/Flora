package uwu.evaware.flora

class Flora<T : Any> {
    private val listeners = sortedSetOf<Listener<T>>()

    fun subscribe(priority: Int = 0, handler: (T) -> Unit): Subscription {
        val listener = Listener(priority, handler)
        synchronized(listeners) { listeners.add(listener) }
        return Subscription { unsubscribe(listener) }
    }

    fun unsubscribe(listener: Listener<T>) {
        synchronized(listeners) { listeners.remove(listener) }
    }

    fun post(event: T) {
        val snapshot = synchronized(listeners) { listeners.toList() }
        for (listener in snapshot.reversed()) {
            listener.handler(event)
        }
    }
}
