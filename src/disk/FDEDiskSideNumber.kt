package disk

import util.FDEUByteHelper

class FDEDiskSideNumber(val code: UByte) {
    companion object {
        fun fromByte(byte: UByte) = when (byte) {
            FDEUByteHelper.ZERO -> SIDE_A
            FDEUByteHelper.ONE -> SIDE_B
            else -> UNKNOWN(byte)
        }

        val SIDE_A = FDEDiskSideNumber(FDEUByteHelper.ZERO)
        val SIDE_B = FDEDiskSideNumber(FDEUByteHelper.ONE)
        val UNKNOWN = {c: UByte -> FDEDiskSideNumber(c) }
    }
}