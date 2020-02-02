package disk.block

import disk.*
import util.BCDInt
import util.ByteParseStream
import util.ExplicitUByteArray

class FDEDiskInfoBlock : FDEDiskBlock(FDEDiskBlockType.INFO) {
    var verified: Boolean = false
    var manufacturerCode: UByte = 0u
    var gameName: FDEGameName? = null
    var gameType: FDEGameType = FDEGameType.NORMAL
    var gameRevision: UByte = 0u
    var sideNumber: FDEDiskSideNumber =
        FDEDiskSideNumber.SIDE_A
    var diskNumber: UByte = 0u
    var diskType: FDEDiskType = FDEDiskType.NORMAL
    var unknown1: UByte = 0u
    var bootFile: UByte = 0u
    var unknown2: ExplicitUByteArray = ExplicitUByteArray(5)
    var manufacturingDate: FDEDiskDate? = null
    var countryCode: UByte = 0u
    var unknown3: UByte = 0u
    var unknown4: UByte = 0u
    var unknown5: ExplicitUByteArray = ExplicitUByteArray(2)
    var unknown6: ExplicitUByteArray = ExplicitUByteArray(5)
    var rewriteDate: FDEDiskDate? = null
    var unknown7: UByte = 0u
    var unknown8: UByte = 0u
    var diskWriterSerial: ExplicitUByteArray = ExplicitUByteArray(2)
    var unknown9: UByte = 0u
    var rewriteCount: BCDInt = BCDInt(0u)
    var actualSide: FDEDiskSideNumber =
        FDEDiskSideNumber.SIDE_A
    var unknown10: UByte = 0u
    var price: UByte = 0u

    companion object {
        val VERIFICATION_CODE = ubyteArrayOf(0x2Au, 0x4Eu, 0x49u, 0x4Eu, 0x54u, 0x45u, 0x4Eu, 0x44u, 0x4Fu, 0x2Du, 0x48u, 0x56u, 0x43u, 0x2Au)
        fun parseFromBytes(stream: ByteParseStream): FDEDiskInfoBlock {
            val blockHeader = stream.getByte()
            if(blockHeader != (0x01u).toUByte())
                throw Exception("Invalid block when block info was expected! (Expected: 0x01 | Recieved: ${blockHeader.toString(16)})")

            val block = FDEDiskInfoBlock()
            val verifyCheck = stream.getBytes(14)
            block.verified = true
            for(b in verifyCheck.indices) {
                if(verifyCheck[b] != VERIFICATION_CODE[b]) {
                    block.verified = false
                    break
                }
            }
            block.manufacturerCode = stream.getByte()
            block.gameName = FDEGameName(stream.getBytes(3))
            block.gameType = FDEGameType.fromByte(stream.getByte())
            block.gameRevision = stream.getByte()
            block.sideNumber = FDEDiskSideNumber.fromByte(stream.getByte())
            block.diskNumber = stream.getByte()
            block.diskType = FDEDiskType.fromByte(stream.getByte())
            block.unknown1 = stream.getByte()
            block.bootFile = stream.getByte()
            block.unknown2 = stream.getBytesExplicit(5)
            block.manufacturingDate =
                FDEDiskDate(stream.getByteAsBCD(), stream.getByteAsBCD(), stream.getByteAsBCD())
            block.countryCode = stream.getByte()
            block.unknown3 = stream.getByte()
            block.unknown4 = stream.getByte()
            block.unknown5 = stream.getBytesExplicit(2)
            block.unknown6 = stream.getBytesExplicit(5)
            block.rewriteDate =
                FDEDiskDate(stream.getByteAsBCD(), stream.getByteAsBCD(), stream.getByteAsBCD())
            block.unknown7 = stream.getByte()
            block.unknown8 = stream.getByte()
            block.diskWriterSerial = stream.getBytesExplicit(2)
            block.unknown9 = stream.getByte()
            block.rewriteCount = stream.getByteAsBCD()
            block.actualSide = FDEDiskSideNumber.fromByte(stream.getByte())
            block.unknown10 = stream.getByte()
            block.price = stream.getByte()
            return block
        }
    }
}