package test1.task2

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class SparseVectorTest {
    val firstVector = SparseVector<Int>()
    val secondVector = SparseVector<Int>()

    @Test
    fun setAndGetTest() {
        firstVector.addAll(listOf(Pair(1, 12), Pair(5, 7), Pair(6, 9), Pair(9, 13), Pair(13, 0)))
        val expected = listOf<Int>(0, 12, 0, 0, 0, 7, 9, 0, 0, 13, 0, 0, 0, 0)
        for (i in 0..13) {
            assertEquals(expected[i], firstVector[i])
        }
    }

    @Test
    fun plusTest() {
        firstVector.addAll(listOf(Pair(1, 5), Pair(5, 7), Pair(6, 2), Pair(9, -10)))
        secondVector.addAll(listOf(Pair(1, 5), Pair(3, 100), Pair(6, -3), Pair(9, -2)))
        val sum = firstVector + secondVector
        val expected = listOf<Int>(0, 10, 0, 100, 0, 7, -1, 0, 0, -12)
        for (i in 0..9) {
            assertEquals(expected[i], sum[i])
        }
    }

    @Test
    fun minusTest() {
        firstVector.addAll(listOf(Pair(1, 5), Pair(5, 7), Pair(6, 2), Pair(9, -10)))
        secondVector.addAll(listOf(Pair(1, 5), Pair(3, 100), Pair(6, -3), Pair(9, -2)))
        val subtract = firstVector - secondVector
        val expected = listOf<Int>(0, 0, 0, -100, 0, 7, 5, 0, 0, -8)
        for (i in 0..9) {
            assertEquals(expected[i], subtract[i])
        }
    }

    @Test
    fun scalarProductTest() {
        firstVector.addAll(listOf(Pair(1, 5), Pair(5, 7), Pair(6, 2), Pair(9, -10)))
        secondVector.addAll(listOf(Pair(1, 5), Pair(3, 100), Pair(6, -3), Pair(9, -2)))
        val scalar = firstVector * secondVector
        val expected = 39
        assertEquals(expected, scalar)
    }
}
