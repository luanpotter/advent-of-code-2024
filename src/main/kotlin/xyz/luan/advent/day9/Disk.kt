package xyz.luan.advent.day9

import java.util.*

class Disk {
    fun parse(diskMap: String): List<Block> {
        var isFile = true
        var fileId = 0

        val result = LinkedList<Block>()
        for (char in diskMap) {
            val size = char.digitToInt()
            val block = if (isFile) {
                File(fileId++)
            } else {
                Space
            }
            repeat(size) {
                result.add(block)
            }
            isFile = !isFile
        }
        return result
    }


    sealed interface Block

    @JvmInline
    value class File(
        private val id: Int,
    ) : Block

    data object Space : Block
}