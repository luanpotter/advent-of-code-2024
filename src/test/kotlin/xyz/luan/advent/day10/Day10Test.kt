package xyz.luan.advent.day10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun `simplest case`() {
        val matrix = Hike.parse(
            """
            0123
            1234
            8765
            9876
        """.lines(),
        )
        val hike = Hike(matrix)
        assertEquals(1, hike.computeA())
        assertEquals(16, hike.computeB())
    }

    @Test
    fun `complex case`() {
        val matrix = Hike.parse(
            """
                89010123
                78121874
                87430965
                96549874
                45678903
                32019012
                01329801
                10456732
        """.lines(),
        )
        val hike = Hike(matrix)
        assertEquals(36, hike.computeA())
        assertEquals(81, hike.computeB())
    }
}
