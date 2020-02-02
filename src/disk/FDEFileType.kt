package disk

import util.FDEUByteHelper

class FDEFileType(code: UByte) {

    companion object {
        var PROGRAM = FDEFileType(0x00u)
        var CHARACTER = FDEFileType(0x01u)
        var VRAM = FDEFileType(0x02u)
        var UNKNOWN = {c:UByte -> FDEFileType(c) }

        fun fromByte(byte: UByte) = when (byte) {
            FDEUByteHelper.ZERO -> PROGRAM
            FDEUByteHelper.ONE -> CHARACTER
            FDEUByteHelper.TWO -> VRAM
            else -> UNKNOWN(byte)
        }
    }
}