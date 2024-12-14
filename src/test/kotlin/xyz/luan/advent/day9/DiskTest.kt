package xyz.luan.advent.day9

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DiskTest {
    @Test
    fun test1() {
        val result = Disk("12345")
        expectDisk(result, "0..111....22222")
    }

    @Test
    fun test2() {
        val result = Disk("2333133121414131402")
        expectDisk(result, "00...111...2...333.44.5555.6666.777.888899")
    }

    private fun expectDisk(disk: Disk, expected: String) {
        assertEquals(disk.toString(), expected)
    }
}