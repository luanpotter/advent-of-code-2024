package xyz.luan.advent.day10

import xyz.luan.advent.util.*

fun main() {
    val input = Hike.parse(readFile("day10/input"))

    val hike = Hike(input)
    println(
        """
        A: ${hike.computeA()}
        B: ${hike.computeB()}
    """.trimIndent(),
    )
}


class Hike(
    private val matrix: Matrix<Int>,
) {
    fun computeA(): Long {
        val reachableLeaves = mutableMapOf<Coord, Set<Coord>>()

        fun process(coord: Coord): Set<Coord> {
            val me = matrix.get(coord)
            if (me == 9) {
                return setOf(coord)
            }

            val neighbors = matrix.neighborsOf(coord)
            return neighbors
                .filter { matrix.get(it) == me + 1 }
                .flatMap { neighbor -> reachableLeaves.getOrPut(neighbor) { process(neighbor) } }
                .toSet()
        }

        var total = 0L
        for (coord in matrix.coords) {
            if (matrix.get(coord) == 0) {
                val leaves = reachableLeaves.getOrPut(coord) { process(coord) }
                total += leaves.size
            }
        }
        return total
    }

    fun computeB(): Long {
        val reachableLeaves = mutableMapOf<Coord, Int>()

        fun process(coord: Coord): Int {
            val me = matrix.get(coord)
            if (me == 9) {
                return 1
            }

            val neighbors = matrix.neighborsOf(coord)
            return neighbors
                .filter { matrix.get(it) == me + 1 }
                .sumOf { neighbor -> reachableLeaves.getOrPut(neighbor) { process(neighbor) } }
        }

        var total = 0L
        for (coord in matrix.coords) {
            if (matrix.get(coord) == 0) {
                val leaves = reachableLeaves.getOrPut(coord) { process(coord) }
                total += leaves
            }
        }
        return total
    }

    companion object {
        fun parse(input: List<String>): Array<Array<Int>> {
            return input
                .map { line -> line.trim().map { it.digitToInt() }.toTypedArray() }
                .filter { it.isNotEmpty() }
                .toTypedArray()
        }
    }
}
