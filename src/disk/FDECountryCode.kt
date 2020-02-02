package disk

class FDECountryCode(val code: UByte) {

    override fun toString() = when(code) {
        0x49u.toUByte() -> "Japan"
        else -> "Unknown (0x${String.format("%02x", code.toInt())})"
    }

    companion object {
        fun fromByte(byte: UByte) = when(byte) {
            0x49u.toUByte() -> JAPAN
            else -> UNKNOWN(byte)
        }

        val JAPAN = FDECountryCode(0x49u)
        val UNKNOWN = {c:UByte -> FDECountryCode(c)}
    }
}