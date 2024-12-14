package xyz.luan.advent.day9

import java.util.*

data class Disk(
    val blocks: MutableList<Block>,
) {
    constructor(diskMap: String) : this(parse(diskMap).toMutableList())

    override fun toString(): String {
        return blocks.joinToString("")
    }

    fun simpleDefrag() {
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

    fun advancedDefrag() {
        val filesInfo = computeFilesToSize().toMutableList()

        for (info in filesInfo) {
            var idx = 0
            while (idx < blocks.size) {
                var size = 0
                while (blocks.getOrNull(idx + size) is Space) {
                    size++
                }
                if (size >= info.size) {
                    break
                }
                idx += size + 1
            }
            if (idx == blocks.size) {
                break
            }

            if (idx < info.position) {
                for (x in 0..<info.size) {
                    blocks[idx + x] = File(id = info.fileId)
                    blocks[info.position + x] = Space
                }
            }
        }
    }

    private data class FileInfo(
        val fileId: Long,
        val position: Int,
        var size: Int = 0,
    )

    private fun computeFilesToSize(): List<FileInfo> {
        val result = mutableMapOf<Long, FileInfo>()
        for ((idx, block) in blocks.withIndex()) {
            if (block is File) {
                val fileId = block.id
                result.getOrPut(fileId) { FileInfo(fileId, idx) }.size++
            }
        }
        return result.values.toList().sortedBy { -it.fileId }
    }

    fun checksum(): Long {
        return blocks.mapIndexed { idx, el -> idx * el.checksum() }.sum()
    }

    companion object {
        fun parse(diskMap: String): List<Block> {
            var fileId = 0L

            val result = LinkedList<Block>()
            for ((idx, char) in diskMap.withIndex()) {
                val size = char.digitToInt()
                val block = if (idx % 2 == 0) {
                    File(fileId++)
                } else {
                    Space
                }
                repeat(size) {
                    result.add(block)
                }
            }

            return result
        }
    }
}