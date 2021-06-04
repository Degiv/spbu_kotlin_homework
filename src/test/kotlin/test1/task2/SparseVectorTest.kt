package test1.task2

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class SparseVectorTest {
    val firstVector = SparseVector()
    val secondVector = SparseVector()
    @Test
    fun setAndGetTest() {
        firstVector[1] = 12
        firstVector[5] = 7
        firstVector[6] = 9
        firstVector[9] = 13
        firstVector[13] = 0
        val expected = listOf<Int>(0, 12, 0, 0, 0, 7, 9, 0, 0, 13, 0, 0, 0, 0)
        for (i in 0..13) {
            assertEquals(expected[i], firstVector[i])
        }
    }

    @Test
    fun plusTest() {
        firstVector[1] = 5
        firstVector[5] = 7
        firstVector[6] = 2
        firstVector[9] = -10
        secondVector[1] = 5
        secondVector[3] = 100
        secondVector[6] = -3
        secondVector[9] = -2
        val sum = firstVector + secondVector
        val expected = listOf<Int>(0, 10, 0, 100, 0, 7, -1, 0, 0, -12)
        for (i in 0..9) {
            assertEquals(expected[i], sum[i])
        }
    }

    @Test
    fun minusTest() {
        firstVector[1] = 5
        firstVector[5] = 7
        firstVector[6] = 2
        firstVector[9] = -10
        secondVector[1] = 5
        secondVector[3] = 100
        secondVector[6] = -3
        secondVector[9] = -2
        val subtract = firstVector - secondVector
        val expected = listOf<Int>(0, 0, 0, -100, 0, 7, 5, 0, 0, -8)
        for (i in 0..9) {
            assertEquals(expected[i], subtract[i])
        }
    }

    @Test
    fun scalarProductTest() {
        firstVector[1] = 5
        firstVector[5] = 7
        firstVector[6] = 2
        firstVector[9] = -10
        secondVector[1] = 5
        secondVector[3] = 100
        secondVector[6] = -3
        secondVector[9] = -2
        val scalar = firstVector * secondVector
        val expected = 39
        assertEquals(expected, scalar)
    }
}
