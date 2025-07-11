package uwu.evaware.flora.api

import uwu.evaware.flora.Subscription

class ListenerBank<T : Any> {
    private var handlers = arrayOfNulls<(T) -> Unit>(4)
    private var size = 0

    fun add(handler: (T) -> Unit): Subscription {
        if (size == handlers.size) {
            handlers = handlers.copyOf(handlers.size * 2)
        }
        handlers[size++] = handler
        return object : Subscription {
            var unsubscribed = false
            override fun unsubscribe() {
                if (unsubscribed) return
                for (i in 0 until size) {
                    if (handlers[i] === handler) {
                        handlers.copyInto(handlers, i, i + 1, size)
                        handlers[--size] = null
                        break
                    }
                }
                unsubscribed = true
            }
        }
    }

    fun invoke(event: T) {
        for (i in 0 until size) {
            handlers[i]?.invoke(event)
        }
    }
}
