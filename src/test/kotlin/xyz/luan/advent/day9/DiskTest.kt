package xyz.luan.advent.day9

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DiskTest {
    private val disk = Disk()

    @Test
    fun test1() {
        val result = disk.parse("12345")
        val expected = parseExpected("0..111....22222")
        assertEquals(expected, result)
    }

    @Test
    fun test2() {
        val result = disk.parse("2333133121414131402")
        val expected = parseExpected("00...111...2...333.44.5555.6666.777.888899")
        assertEquals(expected, result)
    }

    private fun parseExpected(expected: String): List<Disk.Block> {
        return expected.map { if (it == '.') Disk.Space else Disk.File(it.digitToInt()) }
    }
}