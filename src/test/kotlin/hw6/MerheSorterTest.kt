package hw6

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MergeSorterTest {
    companion object {
        @JvmStatic
        fun inputData(): List<Arguments> = listOf(
            Arguments.of(
                intArrayOf(1, 11, 13, 31, 177, 1001), intArrayOf(11, 1, 1001, 13, 177, 31), 1
            ),
            Arguments.of(
                intArrayOf(1, 11, 13, 31, 177, 1001), intArrayOf(11, 1, 1001, 13, 177, 31), 2
            ),
            Arguments.of(
                intArrayOf(1, 11, 13, 31, 177, 1001), intArrayOf(11, 1, 1001, 13, 177, 31), 4
            ),
            Arguments.of(
                intArrayOf(1, 11, 13, 31, 177, 1001), intArrayOf(11, 1, 1001, 13, 177, 31), 8
            ),
            Arguments.of(
                intArrayOf(11, 213, 553, 3100, 17777, 1001001), intArrayOf(213, 11, 1001001, 553, 17777, 3100), 2
            ),
            Arguments.of(
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), intArrayOf(5, 2, 9, 1, 3, 6, 8, 4, 11, 10, 7), 16
            ),
            Arguments.of(
                intArrayOf(), intArrayOf(), 1
            ),
        )
    }

    @MethodSource("inputData")
    @ParameterizedTest(name = "test{index}, {1}")
    fun mergeSortingTest(expectedArray: IntArray, actualArray: IntArray, threadsNumber: Int) {
        MergeSorter().mergeSortMT(actualArray, threadsNumber)
        assertArrayEquals(expectedArray, actualArray)
    }
}
