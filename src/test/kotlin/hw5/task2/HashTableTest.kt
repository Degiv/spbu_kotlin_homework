package hw5.task2

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

internal class HashTableTest {
    private val hashTable = HashTable<String, Int>(PolynomialHashFunction())

    @Test
    fun add() {
        val dataList = listOf(Pair("q", 1), Pair("w", 2), Pair("e", 3),  Pair("r", 4))
        dataList.forEach { hashTable.add(it.first, it.second) }
        dataList.forEach { assertEquals(it.second, hashTable[it.first]) }
    }

    @Test
    fun remove() {
        val dataList = listOf(Pair("q", 1), Pair("w", 2), Pair("e", 3),  Pair("r", 4))
        dataList.forEach { hashTable.add(it.first, it.second) }
        hashTable.remove("q")
        hashTable.remove("e")
        assertEquals(null, hashTable["q"])
        assertEquals(null, hashTable["e"])
    }

    @Test
    fun contains() {
        val dataList = listOf(Pair("q", 1), Pair("w", 2), Pair("e", 3),  Pair("r", 4))
        dataList.forEach { hashTable.add(it.first, it.second) }
        assertEquals(true, hashTable.contains("w"))
        assertEquals(false, hashTable.contains("z"))
        assertEquals(false, hashTable.contains("ww"))
    }

    /**@Test
    fun setHashFunction() {
        val dataList = listOf(Pair("q", 1), Pair("w", 2), Pair("e", 3),  Pair("r", 4))
        dataList.forEach { hashTable.add(it.first, it.second) }
        hashTable.setHashFunction(XorHashFunction())
        dataList.forEach { assertEquals(it.second, hashTable[it.first]) }
        hashTable.setHashFunction(SumHashFunction())
        dataList.forEach { assertEquals(it.second, hashTable[it.first]) }
        hashTable.setHashFunction(PolynomialHashFunction())
        dataList.forEach { assertEquals(it.second, hashTable[it.first]) }
    }**/
}
