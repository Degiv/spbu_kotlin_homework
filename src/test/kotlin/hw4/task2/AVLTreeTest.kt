package hw4.task2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AVLTreeTest {
    @Test
    fun containsKeyTest() {
        val tree = AVLTree<Int, String>()
        val elements = setOf(Pair(1, "q"), Pair(2, "w"), Pair(3, "e"), Pair(4, "r"), Pair(5, "t"))
        elements.forEach { tree.add(it.first, it.second) }
        elements.forEach { assertTrue(tree.containsKey(it.first)) }
    }

    @Test
    fun containsValueTest() {
        val tree = AVLTree<Int, String>()
        val elements = setOf(Pair(1, "q"), Pair(2, "w"), Pair(3, "e"), Pair(4, "r"), Pair(5, "t"))
        elements.forEach { tree.add(it.first, it.second) }
        elements.forEach { assertTrue(tree.containsValue(it.second)) }
    }

    @Test
    fun getTest() {
        val tree = AVLTree<Int, String>()
        val elements = setOf(Pair(1, "q"), Pair(2, "w"), Pair(3, "e"), Pair(4, "r"), Pair(5, "t"))
        elements.forEach { tree.add(it.first, it.second) }
        elements.forEach { assertEquals(it.second, tree[it.first]) }
    }

    @Test
    fun isEmptyTest() {
        val voidTree = AVLTree<Int, Int>()
        assertTrue(voidTree.isEmpty())
    }

    @Test
    fun addTest() {
        val tree = AVLTree<Int, String>()
        val elements = setOf(Pair(1, "q"), Pair(2, "w"), Pair(3, "e"), Pair(4, "r"), Pair(5, "t"))
        elements.forEach { tree.add(it.first, it.second) }
        assertEquals(elements, tree.entries.map { Pair(it.key, it.value) }.toSet())
    }

    @Test
    fun removeTest() {
        val tree = AVLTree<Int, String>()
        val elements = setOf(Pair(1, "q"), Pair(2, "w"), Pair(3, "e"), Pair(4, "r"), Pair(5, "t"))
        elements.forEach { tree.add(it.first, it.second) }
        for (i in 1..4) {
            tree.remove(i)
        }
        tree.printPrefix()
        assertEquals(elements.filter { it.first > 4 }.toSet(), tree.entries.map { Pair(it.key, it.value) }.toSet())
    }
}