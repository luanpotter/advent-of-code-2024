package xyz.luan.advent.day12

import xyz.luan.advent.util.*
import kotlin.math.max
import kotlin.math.min

fun main() {
    val garden = Garden.parse(readFile("day12/input"))
        .buildGraph()
    println("A: ${garden.computeA()}")
    println("B: ${garden.computeB()}")
}

enum class Side { TOP, RIGHT, BOTTOM, LEFT }

data class FenceBit(
    val from: Coord,
    val to: Coord,
    val inside: Side,
) {
    val y: Int? get() = setOf(from.y, to.y).singleOrNull()
    val x: Int? get() = setOf(from.x, to.x).singleOrNull()

    fun tryCombine(other: FenceBit): FenceBit? {
        val side = if (inside == other.inside) inside else return null

        val otherX = other.x
        val otherY = other.y

        if (otherX != null && otherX == x) {
            val minY = min(from.y, to.y)
            val maxY = max(from.y, to.y)
            if ((other.from.y > maxY && other.to.y > maxY) || (other.from.y < minY && other.to.y < minY)) {
                return null
            }
            val otherMinY = min(other.from.y, other.to.y)
            val otherMaxY = max(other.from.y, other.to.y)
            return FenceBit(otherX to min(minY, otherMinY), otherX to max(maxY, otherMaxY), side)
        } else if (otherY != null && otherY == y) {
            val minX = min(from.x, to.x)
            val maxX = max(from.x, to.x)
            if ((other.from.x > maxX && other.to.x > maxX) || (other.from.x < minX && other.to.x < minX)) {
                return null
            }
            val otherMinX = min(other.from.x, other.to.x)
            val otherMaxX = max(other.from.x, other.to.x)
            return FenceBit(min(minX, otherMinX) to otherY, max(maxX, otherMaxX) to otherY, side)
        }
        return null
    }

    companion object {
        fun between(
            c1: Coord,
            c2: Coord,
        ): FenceBit {
            return if (c1.x == c2.x) {
                val x = c1.x
                val y = max(c1.y, c2.y)
                val side = if (c1.y > c2.y) Side.TOP else Side.BOTTOM
                FenceBit(x to y, x + 1 to y, side)
            } else if (c1.y == c2.y) {
                val x = max(c1.x, c2.x)
                val y = c1.y
                val side = if (c1.x > c2.x) Side.LEFT else Side.RIGHT
                FenceBit(x to y, x to y + 1, side)
            } else {
                error("Invalid fence bit: $c1 -> $c2")
            }
        }
    }
}

class Garden(
    private val plots: Matrix<Char>,
) {
    fun buildGraph(): GardenGraph {
        val graph = mutableListOf<Set<Coord>>()

        val toProcess = mutableSetOf(Coord(0, 0))
        while (toProcess.isNotEmpty()) {
            val cell = toProcess.pop()
            val group = mutableSetOf(cell)
            val toCheck = mutableListOf(cell)

            while (toCheck.isNotEmpty()) {
                val current = toCheck.removeFirst()
                val currentValue = plots.get(current)
                if (currentValue == EMPTY) {
                    continue
                }
                plots.set(current, EMPTY)

                val neighbors = plots.neighborsOf(current)
                for (neighbor in neighbors) {
                    val value = plots.get(neighbor)
                    if (value == EMPTY) {
                        continue
                    } else if (value == currentValue) {
                        group.add(neighbor)
                        toCheck.add(neighbor)
                        toProcess.remove(neighbor)
                    } else {
                        toProcess.add(neighbor)
                    }
                }
            }

            graph.add(group)
        }
        return GardenGraph(plots, graph)
}

class GardenGraph(
    private val plots: Matrix<Char>,
    private val graph: List<Set<Coord>>,
) {
    fun computeA(): Long {
        return graph.sumOf { region ->
            val area = region.size.toLong()
            val perimeter = region.sumOf { plot ->
                val neighbors = plots.neighborsOf(plot)
                val edges = 4 - neighbors.size
                val interior = neighbors.count { it !in region }
                (edges + interior).toLong()
            }
            area * perimeter
        }
    }

    fun computeB(): Long {
        fun simplify(bits: MutableSet<FenceBit>) {
            for (b1 in bits.toList()) {
                for (b2 in bits.toList()) {
                    if (b1 == b2) {
                        continue
                    }
                    val combined = b1.tryCombine(b2)
                    if (combined != null) {
                        bits.remove(b1)
                        bits.remove(b2)
                        bits.add(combined)
                        simplify(bits)
                        return
                    }
                }
            }
        }

        return graph.sumOf { region ->
            val area = region.size.toLong()
            val fenceBits = region
                .flatMap { plot ->
                    plots.allNeighborsOf(plot)
                        .filter { it !in region }
                        .map { FenceBit.between(plot, it) }
                }
                .toMutableSet()
            simplify(fenceBits)
            val perimeter = fenceBits.size.toLong()
            area * perimeter
        }
    }

    }

    companion object {
        fun parse(input: List<String>): Garden {
            val plots = input
                .map { it.trim() }
                .filterNot { it.isBlank() }
                .map { it.toCharArray().toTypedArray() }
                .toTypedArray()
            return Garden(plots)
        }
    }
}

private const val EMPTY = '\u0000'
