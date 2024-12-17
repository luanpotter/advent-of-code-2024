package xyz.luan.advent.day12

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {
    @Test
    fun `simplest case`() {
        testCase(
            listOf(
                "AAAA",
                "BBCD",
                "BBCC",
                "EEEC",
            ),
            140L,
        )

        testCase(
            listOf(
                "OOOOO",
                "OXOXO",
                "OOOOO",
                "OXOXO",
                "OOOOO",
            ),
            772L,
        )
    }

    private fun testCase(input: List<String>, result: Long) {
        val gardens = Garden.parse(input)
        assertEquals(result, gardens.compute())
    }
}
