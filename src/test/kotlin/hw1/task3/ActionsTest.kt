package hw1.task3

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows

internal class InsertForwardTest {
    private val storageTest = CommandStorage()

    @Test
    fun perform() {
        InsertForward(5).perform(storageTest)
        InsertForward(4).perform(storageTest)
        InsertForward(3).perform(storageTest)
        InsertForward(2).perform(storageTest)
        InsertForward(1).perform(storageTest)
        assertEquals(listOf(1, 2, 3, 4, 5), storageTest.data)
    }

    @Test
    fun undo() {
        InsertForward(5).perform(storageTest)
        InsertForward(4).perform(storageTest)
        InsertForward(3).perform(storageTest)
        InsertForward(2).perform(storageTest)
        InsertForward(1).perform(storageTest)
        repeat(3) { storageTest.undoLastAction() }
        assertEquals(listOf(4, 5), storageTest.data)
    }
}

internal class InsertBackTest {
    private val storageTest = CommandStorage()

    @Test
    fun perform() {
        InsertBack(5).perform(storageTest)
        InsertBack(4).perform(storageTest)
        InsertBack(3).perform(storageTest)
        InsertBack(2).perform(storageTest)
        InsertBack(1).perform(storageTest)
        assertEquals(listOf(5, 4, 3, 2, 1), storageTest.data)
    }

    @Test
    fun undo() {
        InsertBack(5).perform(storageTest)
        InsertBack(4).perform(storageTest)
        InsertBack(3).perform(storageTest)
        InsertBack(2).perform(storageTest)
        InsertBack(1).perform(storageTest)
        repeat(3) { storageTest.undoLastAction() }
        assertEquals(listOf(5, 4), storageTest.data)
    }
}


internal class MoveTest {
    private val storageTest = CommandStorage()

    @Test
    fun perform() {
        InsertBack(5).perform(storageTest)
        InsertBack(4).perform(storageTest)
        InsertBack(3).perform(storageTest)
        InsertBack(2).perform(storageTest)
        InsertBack(1).perform(storageTest)
        Move(1, 3).perform(storageTest)
        assertEquals(listOf(5, 3, 2, 4, 1), storageTest.data)
    }

    @Test
    fun undo() {
        InsertBack(5).perform(storageTest)
        InsertBack(4).perform(storageTest)
        InsertBack(3).perform(storageTest)
        InsertBack(2).perform(storageTest)
        InsertBack(1).perform(storageTest)
        Move(1, 3).perform(storageTest)
        storageTest.undoLastAction()
        assertEquals(listOf(5, 4, 3, 2, 1), storageTest.data)
    }

    @Test
    fun performOutOfBounds() {
        InsertBack(5).perform(storageTest)
        assertThrows<IllegalStateException> { Move(-1, 2).perform(storageTest) }
    }
}