package uwu.evaware.flora

class Subscription(private val onUnsub: () -> Unit) {
    fun unsubscribe() = onUnsub()
}