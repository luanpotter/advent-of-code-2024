package xyz.luan.advent.day9

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DiskTest {
    @Test
    fun test1() {
        val disk = Disk("12345")
        expectDisk(disk, "0..111....22222")

        disk.defrag()
        expectDisk(disk, "022111222......")

        assertEquals(disk.checksum(), 60)
    }

    @Test
    fun test2() {
        val disk = Disk("2333133121414131402")
        expectDisk(disk, "00...111...2...333.44.5555.6666.777.888899")

        disk.defrag()
        expectDisk(disk, "0099811188827773336446555566..............")

        assertEquals(disk.checksum(), 1928)
    }

    private fun expectDisk(disk: Disk, expected: String) {
        assertEquals(disk.toString(), expected)
    }
}