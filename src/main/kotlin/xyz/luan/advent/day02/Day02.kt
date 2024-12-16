package xyz.luan.advent.day02

import xyz.luan.advent.util.readFile
import kotlin.math.absoluteValue

fun main() {
    val lines = readFile("day02/input")
    val values = lines
        .map { line -> line.split(" ").map { it.trim() }.filter { it.isNotEmpty() }.map { it.toInt() } }
        .filter { it.isNotEmpty() }

    val day02 = Day02(values)
    println("Part A: ${day02.countValid()}")
    println("Part B: ${day02.countValidSkip()}")
}

class Day02(
    private val values: List<List<Int>>,
) {
    fun countValid(): Int {
        return values.count { isValid(it.toMutableList()) }
    }

    fun countValidSkip(): Int {
        return values.count { isValidSkip(it.toMutableList()) }
    }

    private fun isValidSkip(report: MutableList<Int>): Boolean {
        return report.indices.any { skip ->
            isValid(
                report
                    .filterIndexed { index, _ -> index != skip }
                    .toMutableList(),
            )
        }
    }

    private fun isValid(report: MutableList<Int>): Boolean {
        if (report.size <= 1) {
            return true
        }

        var isIncreasing: Boolean? = null
        var previousElement = report.removeAt(0)

        for (element in report) {
            if (previousElement == element) {
                return false
            }
            val currentIsIncreasing = previousElement < element
            if (isIncreasing == null) {
                isIncreasing = currentIsIncreasing
            } else if (isIncreasing != currentIsIncreasing) {
                return false
            }

            val diff = (element - previousElement).absoluteValue
            if (diff !in 1..3) {
                return false
            }

            previousElement = element
        }

        return true
    }
}
