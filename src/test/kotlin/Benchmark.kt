import uwu.evaware.flora.Flora

object Govno : Flora<Dummy>()

fun main() {
    var count = 1
    var time = 0
    val repeat = 10
    val events = 1_000_000

    repeat(1_000) { benchmark(count, false, 1_000) }

    repeat(repeat) {
        time += benchmark(count, true, events)
        count++
    }

    println("completed ${events * repeat}($events x $repeat) in $time ms")
}

fun benchmark(count: Int, print: Boolean = false, events: Int = 100_000): Int {
    val start = System.currentTimeMillis()
    repeat(events) {
        Govno.subscribe {
        }

        Govno.notify(Dummy())
    }
    val end = System.currentTimeMillis()
    val result = (end - start).toInt()

    if (print) println("$count -> ${result}ms")

    return result
}

class Dummy