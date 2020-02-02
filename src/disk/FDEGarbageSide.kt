package disk

import util.ByteParseStream
import util.FDEUByteHelper

class FDEGarbageSide : FDEDiskSide() {
    var pile = FDEGarbagePile()

    override fun toString() = "(unrecognized content block)"

    class FDEGarbagePile {
        var data: UByteArray = ubyteArrayOf()

        override fun toString(): String {
            return "${data.size} bytes"
        }
    }

    companion object {
        fun parseFromBytes(stream: ByteParseStream): FDEGarbageSide {
            val garb = FDEGarbageSide()
            val original = stream.getPosition()
            try {
                while (stream.peek() != FDEUByteHelper.ONE) {
                    stream.skip(1)
                }
            } catch(e: ByteParseStream.EndOfStreamException) {
                // we'll just handle it upstream
            }

            garb.pile.data = stream.getRange(original, stream.getPosition() - 1)
            return garb
        }
    }
}