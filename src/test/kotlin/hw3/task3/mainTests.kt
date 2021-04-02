package hw3.task3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.nio.file.Path

internal class MainTests {
  companion object {
    @JvmStatic
    fun inputData(): List<Arguments> = listOf(
      Arguments.of(
        "test1",
        "test1/Stack.kt"
      ),
      Arguments.of(
        "test2",
        "test2/Person.kt"
      ),
    )
  }

  @MethodSource("inputData")
  @ParameterizedTest(name = "{index} {0}")
  fun getFile(directoryName: String, tempFileName: String, @TempDir tempDirectory: Path) {
    val expectedCode = javaClass.getResource("mainTests/$directoryName/expected.kt").readText()
    val config = javaClass.getResource("mainTests/$directoryName/config.yaml").path
    val file = tempDirectory.resolve(tempFileName)
    generateTestFile(config, tempDirectory.toString())
    assertEquals(expectedCode.replace("\r\n", "\n"), file.toFile().readText().replace("\r\n", "\n"))
  }
}
