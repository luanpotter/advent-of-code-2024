package xyz.luan.advent.util

import java.net.URL

fun readFile(path: String): List<String> {
    return loadResource("xyz/luan/advent/$path").readText().lines()
}

private fun loadResource(path: String): URL {
    val resource = Thread.currentThread().contextClassLoader.getResource(path)
    requireNotNull(resource) { "Resource $path not found" }
    return resource
}

fun <T> MutableSet<T>.pop(): T = first().also { remove(it) }

typealias Coord = Pair<Int, Int>
typealias Matrix<T> = Array<Array<T>>

val <T> Matrix<T>.coords: List<Coord>
    get() = indices.flatMap { x -> this[x].indices.map { y -> x to y } }

fun <T> Matrix<T>.get(coord: Coord): T = this[coord.first][coord.second]

fun <T> Matrix<T>.set(coord: Coord, value: T) {
    this[coord.first][coord.second] = value
}

fun <T> Matrix<T>.neighborsOf(coord: Coord): List<Coord> {
    val (x, y) = coord
    val coords = this.coords
    return directions
        .map { (dx, dy) -> x + dx to y + dy }
        .filter { it in coords }
}

private val directions = setOf(
    -1 to 0,
    1 to 0,
    0 to -1,
    0 to 1,
)



