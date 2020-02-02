package disk.block

import util.ByteParseStream

class FDEDiskDataBlock : FDEDiskBlock(FDEDiskBlockType.DATA) {
    var data: UByteArray = ubyteArrayOf()

    companion object {
        fun parseFromBytes(len: Int, stream: ByteParseStream): FDEDiskDataBlock {
            val blockHeader = stream.getByte()
            if (blockHeader != (0x04u).toUByte())
                throw Exception(
                    "Invalid block when block info was expected! (Expected: 0x04 | Recieved: ${blockHeader.toString(
                        16
                    )})"
                )

            val header = FDEDiskDataBlock()

            header.data = stream.getBytes(len)

            return header
        }
    }
}