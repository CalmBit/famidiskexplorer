package util

class BCDInt(private var byte: UByte) {
    private var _internalInt: Int = FDEBCDUtil.bcdToInt(byte)
    private var _internalByte: UByte = byte

    fun set(b: UByte) {
        _internalInt = FDEBCDUtil.bcdToInt(byte)
        _internalByte = byte
    }

    fun set(i: Int) {
        _internalInt = i
        _internalByte = FDEBCDUtil.intToBCD(i)
    }

    fun asInt(): Int {
        return _internalInt
    }

    fun asUByte(): UByte {
        return _internalByte
    }


}