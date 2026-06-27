package peirce.derek.benchmark.scrabble.count

data class Count(
    internal var _count: Int
) {
    val count get() = _count
}

fun String.counts(): Map<Char, Count> {
    val map = mutableMapOf<Char, Count>()
    forEach { item ->
        map.getOrPut(item) { Count(0) }._count++
    }
    return map
}
