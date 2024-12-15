package xyz.luan.advent.day09

import xyz.luan.advent.util.readFile
import java.nio.ByteBuffer

fun main() {
    val input = readFile("day09/input").first()
    println(
        """
        Simple Defrag: ${Disk(input).also { it.simpleDefrag() }.checksum()}
        Advanced Defrag: ${Disk(input).also { it.advancedDefrag() }.checksum()}
    """.trimIndent(),
    )
}
