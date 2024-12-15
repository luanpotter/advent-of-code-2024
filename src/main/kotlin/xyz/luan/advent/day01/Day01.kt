package xyz.luan.advent.day01

import xyz.luan.advent.util.readFile
import kotlin.math.abs

fun main() {
    val lines = readFile("day01/input")
    val values = lines
        .map { line -> line.split(" ").map { it.trim() }.filter { it.isNotEmpty() }.map { it.toLong() } }
        .filter { it.isNotEmpty() }

    val day01 = Day01(values)
    println(day01.simpleSimilarity())
}

class Day01(
    private val values: List<List<Long>>,
) {
    fun simpleSimilarity(): Long {
        val (list1, list2) = listOf(0, 1).map { idx -> values.map { it[idx] }.sorted() }
        return list1.indices.sumOf { abs(list1[it] - list2[it]) }
    }
}