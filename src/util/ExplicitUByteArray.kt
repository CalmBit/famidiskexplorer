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
        return "{" + _internal.joinToString(",") { b -> "0x${String.format("%02x", b.toInt())}" } + "}"
    }

    fun toLegibleStr(): String {
        return _internal.joinToString("") { b -> b.toByte().toChar().toString() }
    }

    fun toMZString(): String {
        return MZEncoding.fromUByteArray(_internal)
    }

    fun get(i: Int): UByte {
        return _internal[i]
    }
}