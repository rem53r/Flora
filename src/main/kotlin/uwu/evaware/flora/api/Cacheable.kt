package uwu.evaware.flora.api

interface Cacheable<C> {
    val cache: C
    fun refresh()
}