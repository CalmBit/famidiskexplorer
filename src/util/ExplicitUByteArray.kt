package util

class ExplicitUByteArray(val size: Int, arr: UByteArray) {
    constructor(size: Int): this(size, UByteArray(size))

    private val _internal: UByteArray = when {
        arr.size == size -> {
            arr
        }
        arr.size > size -> {
            arr.take(size).toUByteArray()
        }
        else -> {
            var temp = arr.take(size).toUByteArray()
            while(temp.size != size) {
                temp = temp.plus(0x00u)
            }
            temp
        }
    }

    override fun toString(): String {
        return "{" + _internal.joinToString(",") { b -> b.toString(16) } + "}"
    }

    fun toLegibleStr(): String {
        return _internal.joinToString("") { b -> b.toByte().toChar().toString() }
    }
}