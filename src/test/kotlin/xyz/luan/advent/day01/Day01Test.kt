package xyz.luan.advent.day01

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {
    @Test
    fun `simple ordered case`() {
        val day01 = Day01(
            listOf(
                listOf(1, 2),
                listOf(3, 4),
            )
        )
        val result = day01.simpleSimilarity()
        assertEquals(2, result)
    }

    @Test
    fun `simple unordered case`() {
        val day01 = Day01(
            listOf(
                listOf(2, 1),
                listOf(4, 3),
            )
        )
        val result = day01.simpleSimilarity()
        assertEquals(2, result)
    }

    @Test
    fun `advanced similarity`() {
        val day01 = Day01(
            listOf(
                listOf(1, 3),
                listOf(3, 3),
                listOf(5, 3),
                listOf(7, 7),
            )
        )
        val result = day01.advancedSimilarity()
        assertEquals(16, result)
    }
}