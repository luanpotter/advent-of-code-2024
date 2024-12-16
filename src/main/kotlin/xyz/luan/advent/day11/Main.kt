package xyz.luan.advent.day11

import xyz.luan.advent.util.readFile

fun main() {
    val input = parse(readFile("day11/input"))

    val stones = Stones()
    stones.ingest(input)
    stones.evolve(75)
    val result = stones.compute()

    println("B: $result")
}

private fun parse(input: List<String>): List<Long> {
    val line = input.filterNot { it.isBlank() }.single()
    return line
        .split(" ")
        .map { it.trim().toLong() }
}

class Stones {
    private val stones = mutableMapOf<Long, Long>()

    private fun rule(value: Long): List<Long> {
        val str = value.toString()
        return when {
            value == 0L -> listOf(1)
            str.length % 2 == 0 -> listOf(
                str.substring(0..<str.length / 2).toLong(),
                str.substring(str.length / 2).toLong(),
            )

            else -> listOf(value * 2024)
        }
    }

    private fun inc(stone: Long, amount: Long = 1) {
        stones[stone] = (stones[stone] ?: 0) + amount
    }

    fun ingest(stones: List<Long>) {
        stones.forEach { inc(it) }
    }

    fun evolve(blinks: Int) {
        repeat(blinks) { evolveOnce() }
    }

    fun compute(): Long {
        return stones.values.sum()
    }

    private fun evolveOnce() {
        for ((stone, count) in stones.toList()) {
            inc(stone, -count)
            rule(stone).forEach { inc(it, count) }
        }
    }
}
