package uwu.evaware.flora.api

import uwu.evaware.flora.Subscription

interface Subscribable<L, T> {
    fun subscribe(listener: L): Subscription
    fun subscribe(priority: Int, handler: (T) -> Boolean): Subscription
    fun unsubscribe(listener: L)
}