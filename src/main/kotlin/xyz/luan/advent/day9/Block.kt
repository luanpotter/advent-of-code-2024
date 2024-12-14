package xyz.luan.advent.day9

sealed interface Block

@JvmInline
value class File(
    private val id: Int,
) : Block {
    override fun toString(): String = if (id > 9) "*" else id.toString()
}

data object Space : Block {
    override fun toString(): String = "."
}