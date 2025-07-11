package uwu.evaware.flora.api

import uwu.evaware.flora.Subscription

class Dispatcher {
    private val handlers = HashMap<Class<*>, ListenerBank<*>>()

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> subscribe(type: Class<T>, handler: (T) -> Unit): Subscription {
        val list = handlers.getOrPut(type) { ListenerBank<T>() } as ListenerBank<T>
        return list.add(handler)
    }

    fun publish(event: Any) {
        val list = handlers[event.javaClass] ?: return
        @Suppress("UNCHECKED_CAST")
        (list as ListenerBank<Any>).invoke(event)
    }
}
