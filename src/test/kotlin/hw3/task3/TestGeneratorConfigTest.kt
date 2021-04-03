package hw3.task3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class TestGeneratorConfigTest {
    companion object {
        @JvmStatic
        fun inputData(): List<Arguments> = listOf(
            Arguments.of(
                "testConfig1.yaml",
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
                "testConfig2.yaml",
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
    fun getFromYamlTest(inputName: String, expectedConfig: TestGeneratorConfig) {
        assertEquals(expectedConfig, TestGeneratorConfig.getFromYaml(javaClass.getResource(inputName).readText()))
    }
}
