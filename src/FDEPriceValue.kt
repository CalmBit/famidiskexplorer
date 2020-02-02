import org.omg.CORBA.UNKNOWN
import util.FDEUByteHelper

class FDEPriceValue(val code: UByte, val rewritten: Boolean) {

    override fun toString() = when(code) {
        FDEUByteHelper.ZERO -> if(rewritten) "500円" else "3400円"
        FDEUByteHelper.ONE -> if(rewritten) "600円" else "Unknown (0x01, Rewritten: false)"
        0x03u.toUByte() -> if(!rewritten) "3400円 (including peripherals)" else "Unknown (0x03, Rewritten: true)"
        else -> "Unknown (0x${String.format("%02x", code.toInt())}, Rewritten: ${rewritten})"
    }

    companion object {
        val FRESH = FDEPriceValue(0x00u, false)
        val FRESH_WITH_PERIPHERALS = FDEPriceValue(0x03u, false)

        val REWRITTEN = FDEPriceValue(0x00u, true)
        val REWRITTEN_INC = FDEPriceValue(0x01u, true)

        val UNKNOWN = {c: UByte, r:Boolean -> FDEPriceValue(c,r)}

        fun fromByte(byte: UByte, rewritten: Boolean) = when(byte) {
            FDEUByteHelper.ZERO -> if(rewritten) REWRITTEN else FRESH
            FDEUByteHelper.ONE -> if(rewritten) REWRITTEN_INC else UNKNOWN(byte, rewritten)
            0x03u.toUByte() -> if(!rewritten) FRESH_WITH_PERIPHERALS else UNKNOWN(byte, rewritten)
            else -> UNKNOWN(byte, rewritten)
        }
    }
}