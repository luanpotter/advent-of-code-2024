package xyz.luan.advent.day11

import org.junit.jupiter.api.Test
import xyz.luan.advent.day11.Stones
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun `simplest case`() {
        testCase(listOf(125, 17), 6, 22)
        testCase(listOf(125, 17), 25, 55312)
    }

    private fun testCase(input: List<Long>, blink: Int, result: Long) {
        val stones = Stones()
        stones.ingest(input)
        stones.evolve(blink)
        assertEquals(result, stones.compute())
    }
}
