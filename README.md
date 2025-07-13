# Flora

## Event bus for kotlin

## Usage
```kotlin
data class CustomEvent(val text: String)

object Events {
    val customEvent = Flora<CustomEvent>()
}

fun main() {
    val sub: Subscription = Events.customEvent.subscribe {
        println("Text: ${it.text}")
    }

    Events.customEvent.post(CustomEvent("Hello"))

    sub.unsubscribe()
}

```
