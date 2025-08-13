package uwu.evaware.flora.api

interface Notifiable<E> {
    suspend fun notify(event: E): Boolean
}