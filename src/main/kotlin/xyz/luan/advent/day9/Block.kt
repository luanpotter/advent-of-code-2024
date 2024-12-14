package xyz.luan.advent.day9

sealed interface Block {
    fun checksum(): Int
}

@JvmInline
value class File(
    private val id: Int,
) : Block {
    override fun toString(): String = if (id > 9) "*" else id.toString()
    override fun checksum(): Int = id
}

data object Space : Block {
    override fun toString(): String = "."
    override fun checksum(): Int = 0
}