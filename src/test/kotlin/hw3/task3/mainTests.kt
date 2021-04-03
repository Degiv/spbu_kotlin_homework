package hw3.task3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Path
import kotlin.io.path.pathString

internal class MainTests {

    companion object {
        @JvmStatic
        fun inputData(): List<Arguments> = listOf(
            Arguments.of(
                "test1", "common/StackTest.kt"
            ),
            Arguments.of(
                "test2", "meeting/PersonTest.kt"
            )
        )
    }

    @MethodSource("inputData")
    @ParameterizedTest(name = "{index} {0}")
    fun generateTestFile(directoryName: String, tempFileName: String, @TempDir tempDir: Path) {
        val expectedCode = javaClass.getResource("mainTests/$directoryName/expected.kt").readText()
        val config = javaClass.getResource("mainTests/$directoryName/config.yaml").path
        val file = tempDir.resolve(tempFileName)
        generateTestFile(config, tempDir.toString())
        assertEquals(expectedCode, file.toFile().readText())
    }
}
