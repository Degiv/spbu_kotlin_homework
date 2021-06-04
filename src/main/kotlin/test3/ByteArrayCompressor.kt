package test3

fun ByteArray.compress(): ByteArray {
    val listOfRepetitions = mutableListOf<Byte>()
    var previous = this[0]
    var repetitionsCounter= 0

    for (byte in this) {
        if (byte == previous) {
            ++repetitionsCounter
        } else {
            if (repetitionsCounter > 0) {
                listOfRepetitions.add(repetitionsCounter.toByte())
                listOfRepetitions.add(previous)
            }
            previous = byte
            repetitionsCounter = 1
        }
    }

    listOfRepetitions.add(repetitionsCounter.toByte())
    listOfRepetitions.add(previous)
    return listOfRepetitions.toByteArray()
}

enum class ByteType {
    BYTE, TIMES
}

fun ByteArray.decompress(): ByteArray {
    val listOfBytes = mutableListOf<Byte>()
    var currentType = ByteType.BYTE
    var lastByte: Byte = 0
    for (byte in this) {
        if (currentType == ByteType.BYTE) {
            lastByte = byte
            currentType = ByteType.TIMES
        }
        else {
            repeat(byte.toInt()) {
                listOfBytes.add(lastByte)
            }
            currentType = ByteType.BYTE
        }
    }
    return listOfBytes.toByteArray()
}