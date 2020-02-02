package disk

import util.FDEUByteHelper

class FDEDiskType(val code: UByte) {

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