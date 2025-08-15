import uwu.evaware.flora.Flora
import uwu.evaware.flora.Listener
import kotlin.system.measureNanoTime

class GovnoData
object Govno : Flora<GovnoData>()

val listenersList = mutableListOf<Listener<GovnoData>>()

suspend fun main() {
    val events = 1_000_000
    val listeners = 100
    val runs = 15

    var totalTimeMs = 0L
    var totalNsPerOp = 0L

    repeat(runs) { runIndex ->
        val (durationMs, nsPerOp) = benchmark(events, listeners)
        println("Run ${runIndex + 1}: completed $events events & $listeners listeners in $durationMs ms, (ns/op): $nsPerOp")
        totalTimeMs += durationMs
        totalNsPerOp += nsPerOp
    }

    val avgTimeMs = totalTimeMs.toDouble() / runs
    val avgNsPerOp = totalNsPerOp.toDouble() / runs

    println("Average over $runs runs: $avgTimeMs ms total, $avgNsPerOp ns/op")
}

suspend fun benchmark(events: Int = 1_000_000, listeners: Int = 200): Pair<Long, Long> {
    var countLar = 0L
    listenersList.clear()

    repeat(listeners) {
        val listener = Listener<GovnoData>(0) {
            countLar++
            true
        }
        Govno.subscribe(listener)
        listenersList.add(listener)
    }

    val elapsedNs = measureNanoTime {
        repeat(events) {
            Govno.notify(GovnoData())
        }
    }

    listenersList.forEach { Govno.unsubscribe(it) }
    listenersList.clear()

    val totalCalls = events.toLong() * listeners
    val nsPerOp = elapsedNs / totalCalls

    return Pair(elapsedNs / 1_000_000, nsPerOp)
}