package uwu.evaware.flora

import java.util.concurrent.ConcurrentSkipListSet

abstract class Flora<T> {
    private val listeners = ConcurrentSkipListSet<Listener<T>>()

    fun subscribe(priority: Int = 0, handler: (T) -> Unit): Subscription {
        val listener = Listener(priority, handler)
        listeners.add(listener)
        return Subscription { unsubscribe(listener) }
    }

    fun unsubscribe(listener: Listener<T>) {
        listeners.remove(listener)
    }

    fun notify(event: T) {
        listeners.forEach { it.handler(event) }
    }
}