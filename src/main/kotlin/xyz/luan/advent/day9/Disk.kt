package xyz.luan.advent.day9

import java.util.*

data class Disk(
    val blocks: List<Block>,
) {
    constructor(diskMap: String) : this(parse(diskMap))

    override fun toString(): String {
        return blocks.joinToString("")
    }

    companion object {
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
    }


}