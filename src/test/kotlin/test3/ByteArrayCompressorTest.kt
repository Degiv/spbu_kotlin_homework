package test3

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.api.Assertions.assertArrayEquals

internal class ByteArrayCompressorTest {
    companion object {
        @JvmStatic
        fun generalTests(): List<Arguments> = listOf(
            Arguments.of(byteArrayOf(), byteArrayOf()),
            Arguments.of(byteArrayOf(1, 1, 1, 2, 2, 3), byteArrayOf(3, 1, 2, 2, 1, 3)),
            Arguments.of(byteArrayOf(1, 2, 3, 4, 5), byteArrayOf(1, 1, 1, 2, 1, 3, 1, 4, 1, 5)),
            Arguments.of(byteArrayOf(4, 4, 4, 4, 4, 4), byteArrayOf(6, 4)),
        )
    }

    @MethodSource("generalTests")
    @ParameterizedTest(name = "general tests {index}, {0}")
    fun generalTests(decompressedArray: ByteArray, compressedArray: ByteArray) {
        assertArrayEquals(compressedArray, decompressedArray.compress())
        assertArrayEquals(decompressedArray, compressedArray.decompress())
    }
}
