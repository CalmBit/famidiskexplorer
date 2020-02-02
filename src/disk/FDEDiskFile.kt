package disk

import util.ByteParseStream
import util.FDEUByteHelper

class FDEDiskFile {
    var has_header: Boolean = true
    var sides: List<FDEDiskSide> = listOf()


    companion object {
        private val FILE_HEADER = ubyteArrayOf(0x46u, 0x44u, 0x53u, 0x1Au)
        fun parseFromBytes(arr: ByteArray): FDEDiskFile {

            val file = FDEDiskFile()
            var stream = ByteParseStream(arr.toUByteArray())

            val header = stream.getBytes(4)

            for(b in header.indices) {
                if(header[b] != FILE_HEADER[b]) {
                    file.has_header = false
                    break
                }
            }

            if(file.has_header) {
                val disk_side_count = stream.getByte().toInt()

                stream.skip(11)

                try {
                    for (d in 0 until disk_side_count) {
                        if (stream.peek() != FDEUByteHelper.ONE) {
                            val side = FDEGarbageSide.parseFromBytes(stream)
                            file.sides = file.sides.plus(side)
                        }
                        if(!stream.hasEnded()) {
                            val side = FDEDiskSide.parseFromBytes(stream)
                            file.sides = file.sides.plus(side)
                        } else {
                            break
                        }
                    }
                } catch (e: ByteParseStream.EndOfStreamException) {
                    error("File ended before all sides could be parsed!")
                }

                if(!stream.hasEnded()) {
                    val garb = FDEGarbageSide()
                    garb.pile.data = stream.getRemaining()
                    file.sides = file.sides.plus(garb)
                }
            }
            else {
                stream.reset()
                try {
                    while(true) {
                        if (stream.peek() != FDEUByteHelper.ONE) {
                            val side = FDEGarbageSide.parseFromBytes(stream)
                            file.sides = file.sides.plus(side)
                        }
                        if(!stream.hasEnded()) {
                            val side = FDEDiskSide.parseFromBytes(stream)
                            file.sides = file.sides.plus(side)
                        } else {
                            break
                        }
                    }
                } catch (e: ByteParseStream.EndOfStreamException) {
                    println("file manually ended")
                }
            }

            println("done")
            return file
        }
    }


}