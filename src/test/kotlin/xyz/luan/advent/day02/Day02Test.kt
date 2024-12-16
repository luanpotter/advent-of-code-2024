package xyz.luan.advent.day02

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Test {
    private val example = listOf(
        listOf(7, 6, 4, 2, 1),
        listOf(1, 2, 7, 8, 9),
        listOf(9, 7, 6, 2, 1),
        listOf(1, 3, 2, 4, 5),
        listOf(8, 6, 4, 4, 1),
        listOf(1, 3, 6, 7, 9),
    )

    @Test
    fun `simple case`() {
        val day02 = Day02(example)
        assertEquals(2, day02.countValid())
    }

    @Test
    fun `complex case`() {
        val day02 = Day02(example)
        assertEquals(4, day02.countValidSkip())
    }
}
