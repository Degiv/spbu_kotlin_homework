package hw5.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ArithmeticTreeTest {
    companion object {
        @JvmStatic
        fun inputData(): List<Arguments> = listOf(
            Arguments.of("test1.txt", 4),
            Arguments.of("test2.txt", 6),
            Arguments.of("test3.txt", 0 ),
            Arguments.of("test4.txt", 0)
        )
    }

    @MethodSource("inputData")
    @ParameterizedTest(name = "test {index}, {1}")
    fun generalTest(path: String, expectedValue: Int) {
        val tree = ArithmeticTree(javaClass.getResource(path).path)
        assertEquals(expectedValue, tree.calculate())
    }
}
