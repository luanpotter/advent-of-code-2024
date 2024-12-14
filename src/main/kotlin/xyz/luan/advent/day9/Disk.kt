package xyz.luan.advent.day9

import java.util.*

data class Disk(
    val blocks: MutableList<Block>,
) {
    constructor(diskMap: String) : this(parse(diskMap).toMutableList())

    override fun toString(): String {
        return blocks.joinToString("")
    }

    fun defrag() {
        var lastSpace = 0
        for (i in blocks.indices.reversed()) {
            if (blocks[i] is Space) {
                continue
            }
            while (blocks[lastSpace] !is Space && lastSpace < i) {
                lastSpace++
            }
            if (i <= lastSpace) {
                break
            }
            blocks[lastSpace] = blocks[i]
            blocks[i] = Space
        }
    }

    fun checksum(): Int {
        return blocks.mapIndexed { idx, el -> idx * el.checksum() }.sum()
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