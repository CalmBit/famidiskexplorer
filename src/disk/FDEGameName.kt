package disk

import util.ExplicitUByteArray
import util.MZEncoding

class FDEGameName(arr: UByteArray) {
    val arr = ExplicitUByteArray(3, arr)

    override fun toString(): String = arr.toLegibleStr()
}