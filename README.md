# Flora

## Extremely fast event bus for Kotlin

## Usage

```kotlin
class CustomEventData(val text: String)

object CustomEvent : Flora<CustomEventData>()

fun main() {
    // Subscribe to event
    val subscription = CustomEvent.subscribe {
        println("Text: ${it.text}")
    }

    // Notify (post) event
    CustomEvent.notify(CustomEventData("Hello"))

    // Unsubscribe from event
    subscription.unsubscribe()
}
```
