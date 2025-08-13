package uwu.evaware.flora

import uwu.evaware.flora.api.Cacheable
import uwu.evaware.flora.api.Notifiable
import uwu.evaware.flora.api.Subscribable
import java.util.concurrent.ConcurrentSkipListSet

abstract class Flora<T> : Cacheable<List<Listener<T>>>, Subscribable<Listener<T>, T>, Notifiable<T> {
    val listeners = ConcurrentSkipListSet<Listener<T>>()
    @Volatile override var cache: List<Listener<T>> = emptyList()

    override fun refresh() {
        cache = listeners.toList()
    }

    override fun subscribe(listener: Listener<T>): Subscription {
        if (listeners.add(listener)) refresh()
        return Subscription { unsubscribe(listener) }
    }

    override fun subscribe(priority: Int, handler: (T) -> Boolean): Subscription {
        return subscribe(Listener(priority, handler))
    }

    override fun unsubscribe(listener: Listener<T>) {
        if (listeners.remove(listener)) refresh()
    }

    override suspend fun notify(event: T): Boolean {
        for (listener in cache) {
            if (!listener.handler(event)) return false
        }
        return true
    }
}