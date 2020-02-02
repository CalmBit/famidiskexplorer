package disk.block

import util.ByteParseStream

class FDEDiskAmountBlock : FDEDiskBlock(FDEDiskBlockType.AMOUNT) {
    var amount: UByte = 0u

    companion object {
        val VERIFICATION_CODE = ubyteArrayOf(0x2Au, 0x4Eu, 0x49u, 0x4Eu, 0x54u, 0x45u, 0x4Eu, 0x44u, 0x4Fu, 0x2Du, 0x48u, 0x56u, 0x43u, 0x2Au)
        fun parseFromBytes(stream: ByteParseStream): FDEDiskAmountBlock {
            val blockHeader = stream.getByte()
            if (blockHeader != (0x02u).toUByte())
                throw Exception(
                    "Invalid block when block info was expected! (Expected: 0x02 | Recieved: ${blockHeader.toString(
                        16
                    )})"
                )

            val amount = FDEDiskAmountBlock()

            amount.amount = stream.getByte()

            return amount
        }
    }
}