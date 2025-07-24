import uwu.evaware.flora.Flora
import uwu.evaware.flora.Subscription
import kotlin.system.measureNanoTime

class TestEvent

fun main() {
    listOf(1_000, 10_000, 100_000, 1_000_000).forEach { n ->
        println("- Benchmark: $n")
        val flora = Flora<TestEvent>()
        val subs = mutableListOf<Subscription>()

        val subscribeTimeMs = measureNanoTime {
            repeat(n) { i ->
                subs += flora.subscribe(priority = i % 16) {}
            }
        } / 1_000_000

        val postTimeMs = measureNanoTime {
            repeat(n) { i ->
                flora.post(TestEvent())
            }
        } / 1_000_000

        val unsubscribeTimeMs = measureNanoTime {
            subs.forEach { it.unsubscribe() }
        } / 1_000_000

        println("Subscribe: ${subscribeTimeMs}ms")
        println("Post: ${postTimeMs}ms")
        println("Unsubscribe: ${unsubscribeTimeMs}ms")
        println()
    }
}