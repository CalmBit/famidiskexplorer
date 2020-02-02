package disk

import util.ExplicitUByteArray

class FDEFileName(arr: UByteArray) {
    val arr = ExplicitUByteArray(8, arr)

    override fun toString(): String = arr.toLegibleStr()
}