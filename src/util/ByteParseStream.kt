package util

class ByteParseStream(private val _bytes: UByteArray) {
    private var _iterator: Int = 0

    fun getByte() = _bytes[_iterator++]

    fun getBytes(len: Int): UByteArray {
        try {
            val l = List(len) { i -> _bytes[_iterator + i] }.toUByteArray()
            _iterator += len

            return l
        } catch(e: ArrayIndexOutOfBoundsException) {
            throw EndOfStreamException()
        }
    }

    fun skip(len: Int) {
        _iterator += len
        if(_iterator >= _bytes.size)
            throw EndOfStreamException()
    }

    fun getBytesExplicit(len: Int) = ExplicitUByteArray(len, this.getBytes(len))

    fun getByteAsBCD(): BCDInt = BCDInt(this.getByte())
    fun getPosition() = _iterator

    fun backUp(len: Int) {
        _iterator -= len
    }

    fun getRange(first: Int, last: Int): UByteArray {
        val now = _iterator
        _iterator = first
        val res = getBytes(last-first)
        _iterator = now
        return res
    }

    fun peek() = _bytes[_iterator]
    fun reset() {
        _iterator = 0
    }
    fun getShort(): UShort = (this.getByte().toUInt() + (this.getByte().toUInt() shl 8)).toUShort()
    fun getRemaining() = getBytes(_bytes.size-_iterator)
    fun hasEnded() = _iterator >= _bytes.size

    class EndOfStreamException : Exception() {

    }
}