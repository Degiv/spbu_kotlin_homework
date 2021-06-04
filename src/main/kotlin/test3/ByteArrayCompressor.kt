package test3

fun ByteArray.compress(): ByteArray {
    if (this.isEmpty()) {
        return this
    }

    val listOfRepetitions = mutableListOf<Byte>()
    var previous = this[0]
    var repetitionsCounter = 0

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
    var currentType = ByteType.TIMES
    var lastTimes: Byte = 0
    for (byte in this) {
        if (currentType == ByteType.BYTE) {
            repeat(lastTimes.toInt()) {
                listOfBytes.add(byte)
            }
            currentType = ByteType.TIMES
        } else {
            lastTimes = byte
            currentType = ByteType.BYTE
        }
    }
    return listOfBytes.toByteArray()
}
