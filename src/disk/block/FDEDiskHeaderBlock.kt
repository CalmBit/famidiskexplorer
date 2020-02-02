package disk.block

import disk.FDEFileName
import disk.FDEFileType
import util.ByteParseStream

class FDEDiskHeaderBlock : FDEDiskBlock(FDEDiskBlockType.HEADER) {
    var fileNumber: UByte = 0u
    var fileIndicateCode: UByte = 0u
    var fileName: FDEFileName? = null
    var fileAddress: UShort = 0u
    var fileSize: UShort = 0u
    var fileType: FDEFileType = FDEFileType.PROGRAM

    override fun toString() = "Header Block"

    companion object {
        fun parseFromBytes(stream: ByteParseStream) : FDEDiskHeaderBlock {
            val blockHeader = stream.getByte()
            if(blockHeader != (0x03u).toUByte())
                throw Exception("Invalid block when block info was expected! (Expected: 0x03 | Recieved: 0x${String.format("%02x", blockHeader.toInt())}")

            val header = FDEDiskHeaderBlock()

            header.fileNumber = stream.getByte()
            header.fileIndicateCode = stream.getByte()
            header.fileName = FDEFileName(stream.getBytes(8))
            header.fileAddress = stream.getShort()
            header.fileSize = stream.getShort()
            header.fileType = FDEFileType.fromByte(stream.getByte())

            return header
        }
    }
}