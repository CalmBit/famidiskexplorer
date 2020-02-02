package disk.block

enum class FDEDiskBlockType(code: UByte) {
    INFO(0x01u),
    AMOUNT(0x02u),
    HEADER(0x03u),
    DATA(0x04u)


}