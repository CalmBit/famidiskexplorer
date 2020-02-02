package disk

import util.FDEUByteHelper

class FDEDiskType(val code: UByte) {

    override fun toString() = when (code) {
        FDEUByteHelper.ZERO -> "FMC (Normal)"
        FDEUByteHelper.ONE -> "FSC (Shuttered)"
        else -> "Unknown (0x${code.toString(16)}"
    }

    companion object {
        fun fromByte(byte: UByte) = when (byte) {
            FDEUByteHelper.ZERO -> NORMAL
            FDEUByteHelper.ONE -> SHUTTERED
            else -> UNKNOWN(byte)
        }

        val NORMAL = FDEDiskType(FDEUByteHelper.ZERO)
        val SHUTTERED = FDEDiskType(FDEUByteHelper.ONE)
        val UNKNOWN = {c: UByte -> FDEDiskType(c) }
    }
}