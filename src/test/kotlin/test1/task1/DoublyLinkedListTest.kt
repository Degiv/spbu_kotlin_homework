package test1.task1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class DoublyLinkedListTest {
    val list = DoublyLinkedList<Int>()
    @Test
    fun addAndGetTest() {
        for (i in 0..5) {
            list.add(i)
        }
        for (i in 0..5) {
            assertEquals(i, list.get(i))
        }
    }

    @Test
    fun positionalAddTest() {
        list.add(0, 0)
        list.add(1, 0)
        list.add(2, 1)
        list.add(3, 3)
        list.add(4, 2)
        val expected = listOf(1, 2, 4, 0, 3)
        for (i in 0..4) {
            assertEquals(expected[i], list.get(i))
        }
    }

    @Test
    fun removeTest() {
        for (i in 0..5) {
            list.add(i)
        }
        list.remove(0)
        list.remove(3)
        val expected = listOf(1, 2, 3, 5)
        for (i in 0..3) {
            assertEquals(expected[i], list.get(i))
        }
    }
}
