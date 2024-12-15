package xyz.luan.advent.day09

sealed interface Block {
    fun checksum(): Long
}

@JvmInline
value class File(
    val id: Long,
) : Block {
    override fun toString(): String = if (id > 9) "*" else id.toString()
    override fun checksum(): Long = id
}

data object Space : Block {
    override fun toString(): String = "."
    override fun checksum(): Long = 0
}