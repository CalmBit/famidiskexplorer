package disk

import disk.block.FDEDiskAmountBlock
import disk.block.FDEDiskInfoBlock
import util.ByteParseStream

open class FDEDiskSide {
    var info: FDEDiskInfoBlock = FDEDiskInfoBlock()
    var amount: FDEDiskAmountBlock = FDEDiskAmountBlock()
    var files: List<FDEFileBlockPair> = listOf()

    companion object {
        fun parseFromBytes(stream: ByteParseStream) : FDEDiskSide {
            var side = FDEDiskSide()

            side.info = FDEDiskInfoBlock.parseFromBytes(stream)
            side.amount = FDEDiskAmountBlock.parseFromBytes(stream)
            while(true) {
                if(stream.peek() != 0x03u.toUByte()) {
                    break
                }
                side.files = side.files.plus(FDEFileBlockPair.parseFromBytes(stream))
            }
            println(stream.getPosition())
            return side
        }
    }
}