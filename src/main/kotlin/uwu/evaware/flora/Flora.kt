package uwu.evaware.flora

import uwu.evaware.flora.api.Dispatcher

object Flora {
    private val dispatcher = Dispatcher()

    fun <T : Any> subscribe(type: Class<T>, handler: (T) -> Unit): Subscription {
        return dispatcher.subscribe(type, handler)
    }

    fun publish(event: Any) {
        dispatcher.publish(event)
    }
}
