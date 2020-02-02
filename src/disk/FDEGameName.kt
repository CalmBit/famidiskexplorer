package disk

import util.ExplicitUByteArray

class FDEGameName(arr: UByteArray) {
    val arr = ExplicitUByteArray(3, arr)

    override fun toString(): String = arr.toLegibleStr()
}