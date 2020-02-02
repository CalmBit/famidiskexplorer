package disk

class FDEGameType(val code: UByte) {

    override fun toString()= when (code) {
            0x20u.toUByte() -> "Normal"
            0x45u.toUByte() -> "Event"
            0x52u.toUByte() -> "Reduced Price"
            else -> "Unknown (0x${String.format("%02x", code.toInt())})"
        }

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