package hw1.task3

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.io.File

internal class CommandStorageTest {
    private val storageTest = CommandStorage()

    @Test
    fun addAction() {
        InsertForward(1).perform(storageTest)
        InsertBack(5).perform(storageTest)
        InsertBack(6).perform(storageTest)
        InsertBack(7).perform(storageTest)
        Move(0, 2).perform(storageTest)
        assertEquals(listOf(5, 6, 1, 7), storageTest.data)
    }

    @Test
    fun undoLastAction() {
        InsertForward(1).perform(storageTest)
        InsertBack(5).perform(storageTest)
        InsertBack(6).perform(storageTest)
        InsertBack(7).perform(storageTest)
        Move(0, 2).perform(storageTest)
        repeat(3) { storageTest.undoLastAction() }
        assertEquals(listOf(1, 5), storageTest.data)
    }

    @Test
    fun serialize() {
        InsertBack(1).perform(storageTest)
        InsertBack(2).perform(storageTest)
        InsertBack(3).perform(storageTest)
        InsertBack(4).perform(storageTest)
        val fileWriteTo = "src/test/resources/hw1/task3/ActionList.json"
        val fileEqualTo = "src/test/resources/hw1/task3/equalTo.json"
        storageTest.serialize(fileWriteTo)
        assertEquals(File(fileEqualTo).readText(), File(fileWriteTo).readText())
    }

    @Test
    fun deserialize() {
        val fileReadFrom = "src/test/resources/hw1/task3/readFrom.json"
        storageTest.deserialize(fileReadFrom)
        assertEquals(listOf(7, 5, 3, 1), storageTest.data)
    }
}