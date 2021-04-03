package hw3.task3

import java.io.File

fun generateTestFile(configPath: String, outputPath: String) {
    val yamlText: String = File(configPath).readText()
    val config = TestGeneratorConfig.getFromYaml(yamlText)
    val file = TestGenerator(config).file
    file.writeTo(File(outputPath))
}

fun main() {
    println("Enter the path to yaml config:")
    val configPath = readLine().toString()
    println("Enter the path to file for output:")
    val outputPath = readLine().toString()
    generateTestFile(configPath, outputPath)
}
