package hw1.task3

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

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
    fun serialize(@TempDir tempDir: Path) {
        InsertBack(1).perform(storageTest)
        InsertBack(2).perform(storageTest)
        InsertBack(3).perform(storageTest)
        InsertBack(4).perform(storageTest)
        val fileWriteTo = tempDir.resolve("writeTo.json")
        storageTest.serialize(fileWriteTo.toString())
        assertEquals(javaClass.getResource("ActionListTest.json").readText(), File(fileWriteTo.toString()).readText())
    }

    @Test
    fun deserialize() {
        storageTest.deserialize(javaClass.getResource("ActionListTest.json").path)
        assertEquals(listOf(1, 2, 3, 4), storageTest.data)
    }
}