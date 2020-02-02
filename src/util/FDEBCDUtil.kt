package util

object FDEBCDUtil {
    fun bcdToInt(b: UByte): Int {
        val upper = ((b and 0xF0u).toInt() shr 4)
        val lower = (b and 0x0Fu).toInt()
        return (upper * 10) + lower
    }

    fun intToBCD(i: Int): UByte {
        val upper = (((i / 10) and 0x0F) shl 4).toUByte()
        println(upper.toString(16))
        val lower = ((i - (i/10)*10) and 0x0F).toUByte()
        println(lower.toString(16))
        return (upper + lower).toUByte()
    }
}