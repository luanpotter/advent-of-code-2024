package xyz.luan.advent.day9

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DiskTest {
    private val disk = Disk()

    @Test
    fun testSum() {
        val result = disk.parse("2333133121414131402")
        val expected = "2333133121414131402"
        assertEquals(expected, result)
    }
}