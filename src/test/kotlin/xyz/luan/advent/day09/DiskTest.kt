package xyz.luan.advent.day09

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DiskTest {
    @Test
    fun test1a() {
        val disk = Disk("12345")
        expectDisk(disk, "0..111....22222")

        disk.simpleDefrag()
        expectDisk(disk, "022111222......")

        assertEquals(disk.checksum(), 60)
    }

    @Test
    fun test1b() {
        val disk = Disk("12345")
        expectDisk(disk, "0..111....22222")

        disk.advancedDefrag()
        expectDisk(disk, "0..111....22222")

        assertEquals(disk.checksum(), 132)
    }

    @Test
    fun test2a() {
        val disk = Disk("2333133121414131402")
        expectDisk(disk, "00...111...2...333.44.5555.6666.777.888899")

        disk.simpleDefrag()
        expectDisk(disk, "0099811188827773336446555566..............")

        assertEquals(disk.checksum(), 1928)
    }

    @Test
    fun test2b() {
        val disk = Disk("2333133121414131402")
        expectDisk(disk, "00...111...2...333.44.5555.6666.777.888899")

        disk.advancedDefrag()
        expectDisk(disk, "00992111777.44.333....5555.6666.....8888..")

        assertEquals(disk.checksum(), 2858)
    }

    private fun expectDisk(disk: Disk, expected: String) {
        assertEquals(expected, disk.toString())
    }
}
