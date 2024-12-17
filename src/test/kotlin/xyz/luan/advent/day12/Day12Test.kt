package xyz.luan.advent.day12

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {
    @Test
    fun `part A 1`() {
        testCaseA(
            listOf(
                "AAAA",
                "BBCD",
                "BBCC",
                "EEEC",
            ),
            140L,
        )
    }

    @Test
    fun `part A 2`() {
        testCaseA(
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

    @Test
    fun `part B 1`() {
        testCaseB(
            listOf(
                "EEEEE",
                "EXXXX",
                "EEEEE",
                "EXXXX",
                "EEEEE",
            ),
            236L,
        )
    }

    @Test
    fun `part B 2`() {
        testCaseB(
            listOf(
                "AAAAAA",
                "AAABBA",
                "AAABBA",
                "ABBAAA",
                "ABBAAA",
                "AAAAAA",
            ),
            368L,
        )
    }

    @Test
    fun `fence bits between`() {
        // left of (0, 0)
        val bit1 = FenceBit.between(-1 to 0, 0 to 0)
        assertEquals(0 to 0, bit1.from)
        assertEquals(0 to 1, bit1.to)

        // top of (0, 0)
        val bit2 = FenceBit.between(0 to -1, 0 to 0)
        assertEquals(0 to 0, bit2.from)
        assertEquals(1 to 0, bit2.to)

        // right of (0, 0)
        val bit3 = FenceBit.between(1 to 0, 0 to 0)
        assertEquals(1 to 0, bit3.from)
        assertEquals(1 to 1, bit3.to)

        // bottom of (0, 0)
        val bit4 = FenceBit.between(0 to 1, 0 to 0)
        assertEquals(0 to 1, bit4.from)
        assertEquals(1 to 1, bit4.to)
    }

    @Test
    fun `fence bits adjacent y combine`() {
        val bit1 = FenceBit(0 to 0, 1 to 0, Side.TOP)
        assertEquals(0, bit1.y)

        val bit2 = FenceBit(1 to 0, 2 to 0, Side.TOP)
        assertEquals(0, bit2.y)

        val combined = bit1.tryCombine(bit2)!!
        assertEquals(0 to 0, combined.from)
        assertEquals(2 to 0, combined.to)
    }

    @Test
    fun `fence bits long y combine`() {
        val bit1 = FenceBit(0 to 0, 3 to 0, Side.TOP)
        assertEquals(0, bit1.y)

        val bit2 = FenceBit(3 to 0, 5 to 0, Side.TOP)
        assertEquals(0, bit2.y)

        val combined = bit1.tryCombine(bit2)!!
        assertEquals(0 to 0, combined.from)
        assertEquals(5 to 0, combined.to)
    }

    @Test
    fun `fence bits intersecting y combine`() {
        val bit1 = FenceBit(0 to 0, 3 to 0, Side.TOP)
        assertEquals(0, bit1.y)

        val bit2 = FenceBit(1 to 0, 4 to 0, Side.TOP)
        assertEquals(0, bit2.y)

        val combined = bit1.tryCombine(bit2)!!
        assertEquals(0 to 0, combined.from)
        assertEquals(4 to 0, combined.to)
    }

    @Test
    fun `fence bits inside y combine`() {
        val bit1 = FenceBit(0 to 0, 3 to 0, Side.TOP)
        assertEquals(0, bit1.y)

        val bit2 = FenceBit(1 to 0, 2 to 0, Side.TOP)
        assertEquals(0, bit2.y)

        val combined = bit1.tryCombine(bit2)!!
        assertEquals(0 to 0, combined.from)
        assertEquals(3 to 0, combined.to)
    }

    @Test
    fun `fence bits adjacent x combine`() {
        val bit1 = FenceBit(0 to 0, 0 to 1, Side.RIGHT)
        assertEquals(0, bit1.x)

        val bit2 = FenceBit(0 to 1, 0 to 2, Side.RIGHT)
        assertEquals(0, bit2.x)

        val combined = bit1.tryCombine(bit2)!!
        assertEquals(0 to 0, combined.from)
        assertEquals(0 to 2, combined.to)
    }

    @Test
    fun `fence bits long x combine`() {
        val bit1 = FenceBit(0 to 0, 0 to 3, Side.RIGHT)
        assertEquals(0, bit1.x)

        val bit2 = FenceBit(0 to 3, 0 to 5, Side.RIGHT)
        assertEquals(0, bit2.x)

        val combined = bit1.tryCombine(bit2)!!
        assertEquals(0 to 0, combined.from)
        assertEquals(0 to 5, combined.to)
    }

    @Test
    fun `fence bits intersecting x combine`() {
        val bit1 = FenceBit(0 to 0, 0 to 3, Side.RIGHT)
        assertEquals(0, bit1.x)

        val bit2 = FenceBit(0 to 1, 0 to 4, Side.RIGHT)
        assertEquals(0, bit2.x)

        val combined = bit1.tryCombine(bit2)!!
        assertEquals(0 to 0, combined.from)
        assertEquals(0 to 4, combined.to)
    }

    @Test
    fun `fence bits inside x combine`() {
        val bit1 = FenceBit(0 to 0, 0 to 3, Side.RIGHT)
        assertEquals(0, bit1.x)

        val bit2 = FenceBit(0 to 1, 0 to 2, Side.RIGHT)
        assertEquals(0, bit2.x)

        val combined = bit1.tryCombine(bit2)!!
        assertEquals(0 to 0, combined.from)
        assertEquals(0 to 3, combined.to)
    }

    private fun testCaseA(input: List<String>, result: Long) {
        val gardens = Garden.parse(input)
        assertEquals(result, gardens.buildGraph().computeA())
    }

    private fun testCaseB(input: List<String>, result: Long) {
        val gardens = Garden.parse(input)
        assertEquals(result, gardens.buildGraph().computeB())
    }
}
