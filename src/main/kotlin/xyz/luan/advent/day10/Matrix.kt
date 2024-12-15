package xyz.luan.advent.day10

typealias Matrix<T> = List<List<T>>
typealias Coord = Pair<Int, Int>

val Matrix<*>.coords: List<Coord>
    get() = indices.flatMap { x -> this[x].indices.map { y -> x to y } }

fun <T> Matrix<T>.get(coord: Coord): T = this[coord.first][coord.second]

private val directions = setOf(
    -1 to 0,
    1 to 0,
    0 to -1,
    0 to 1,
)

fun <T> Matrix<T>.neighborsOf(coord: Coord): List<Coord> {
    val (x, y) = coord
    return directions
        .map { (dx, dy) -> x + dx to y + dy }
        .filter { it in coords }
}
