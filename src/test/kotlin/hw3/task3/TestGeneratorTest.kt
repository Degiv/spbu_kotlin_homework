package hw3.task3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class TestGeneratorTest {
    companion object {
        @JvmStatic
        fun inputData(): List<Arguments> = listOf(
            Arguments.of(
                "testGenerator1.kt",
                TestGeneratorConfig(
                    "hw1.task3",
                    "InsertForward",
                    listOf(
                        FunctionName("perform"),
                        FunctionName("undo")
                    )
                )
            ),
            Arguments.of(
                "testGenerator2.kt",
                TestGeneratorConfig(
                    "hw1.task3",
                    "InsertBack",
                    listOf(
                        FunctionName("perform"),
                        FunctionName("undo")
                    )
                )
            )
        )
    }

    @MethodSource("inputData")
    @ParameterizedTest(name = "{index} {0}")
    fun getFile(expectedName: String, testGeneratorConfig: TestGeneratorConfig) {
        assertEquals(
            javaClass.getResource(expectedName).readText(),
            TestGenerator(testGeneratorConfig).file.toString()
        )
    }
}
