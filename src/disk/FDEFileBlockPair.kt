package disk

import disk.block.FDEDiskDataBlock
import disk.block.FDEDiskHeaderBlock
import util.ByteParseStream

class FDEFileBlockPair {
    var header: FDEDiskHeaderBlock = FDEDiskHeaderBlock()
    var data: FDEDiskDataBlock = FDEDiskDataBlock()

    companion object {
        fun parseFromBytes(stream: ByteParseStream) : FDEFileBlockPair {
            val pair = FDEFileBlockPair()
            pair.header = FDEDiskHeaderBlock.parseFromBytes(stream)
            pair.data = FDEDiskDataBlock.parseFromBytes(pair.header.fileSize.toInt(), stream)
            return pair
        }
    }

    override fun toString() = header.fileName?.toString() ?: "UNKNOWN FILE NAME"
}