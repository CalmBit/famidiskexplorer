package disk

class FDEGameType(val code: UByte) {

    companion object {
        fun fromByte(byte: UByte) = when (byte) {
            0x20u.toUByte() -> NORMAL
            0x45u.toUByte() -> EVENT
            0x52u.toUByte() -> REDUCED
            else -> UNKNOWN(byte)
        }

        val NORMAL = FDEGameType(0x20u)
        val EVENT = FDEGameType(0x45u)
        val REDUCED = FDEGameType(0x52u)
        val UNKNOWN = {code: UByte -> FDEGameType(code) }
    }
}