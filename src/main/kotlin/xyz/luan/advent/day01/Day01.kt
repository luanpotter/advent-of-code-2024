package xyz.luan.advent.day01

import xyz.luan.advent.util.readFile
import kotlin.math.abs

fun main() {
    val lines = readFile("day01/input")
    val values = lines
        .map { line -> line.split(" ").map { it.trim() }.filter { it.isNotEmpty() }.map { it.toLong() } }
        .filter { it.isNotEmpty() }

    val day01 = Day01(values)
    println(
        """
            Simple similarity: ${day01.simpleSimilarity()}
            Advanced similarity: ${day01.advancedSimilarity()}
    """.trimIndent(),
    )
}

class Day01(
    private val values: List<List<Long>>,
) {
    private fun parse(): List<List<Long>> {
        return listOf(0, 1).map { idx -> values.map { it[idx] }.sorted() }
    }

    fun simpleSimilarity(): Long {
        val (list1, list2) = parse()
        return list1.indices.sumOf { abs(list1[it] - list2[it]) }
    }

    fun advancedSimilarity(): Long {
        val (list1, list2) = parse()

        val countMap = mutableMapOf<Long, Int>()
        for (el in list2) {
            countMap[el] = (countMap[el] ?: 0) + 1
        }

        return list1.sumOf { it * (countMap[it] ?: 0) }
    }
}