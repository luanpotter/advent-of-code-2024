package xyz.luan.advent.day12

import xyz.luan.advent.util.*

fun main() {
    val garden = Garden.parse(readFile("day12/input"))
    println("A: ${garden.compute()}")
}

class Garden(
    private val plots: Matrix<Char>,
) {
    fun compute(): Long {
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
