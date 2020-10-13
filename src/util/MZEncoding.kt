package util

object MZEncoding {
    private var MASTER = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G",
                        "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
                        "Y", "Z", " ", ",", ".", ".,", "®")


    init {
        MASTER = MASTER.plus(Array(0xFF-MASTER.size) {"?"})
        MASTER[0xF0] = "©"
        MASTER[0xFC] = "©"
    }

    fun fromUByte(byte: UByte): String {
        val index = byte.toInt()
        if (index >= MASTER.size) return "?"
        return MASTER[index]
    }

    fun fromUByteArray(arr: UByteArray): String {
        val builder = StringBuilder()
        for(b in arr) {
            builder.append(fromUByte(b))
        }
        return builder.toString()
    }
}