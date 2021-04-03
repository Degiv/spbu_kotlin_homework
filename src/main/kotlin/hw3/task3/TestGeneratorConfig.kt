package hw3.task3

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Store config for [TestGenerator]
 * @param packageName Package name to create test class in.
 * @param className Class to create test class.
 * @param functions List of functions to test.
 */
@Serializable
data class TestGeneratorConfig(
    @SerialName("package name")
    val packageName: String,
    @SerialName("class name")
    val className: String,
    val functions: List<FunctionName>
) {
    companion object {
        /**
         * Get decoded YAML config.
         * @param yamlText YAML text.
         * @return Config object.
         */
        fun getFromYaml(yamlText: String) = Yaml.default.decodeFromString(this.serializer(), yamlText)
    }
}
