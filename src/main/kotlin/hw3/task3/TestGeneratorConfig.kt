package hw3.task3

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TestGeneratorConfig(
    @SerialName("package name")
    val packageName: String,
    @SerialName("class name")
    val className: String,
    val functions: List<FunctionName>
) {
    companion object {
        fun getFromYaml(yamlText: String) = Yaml.default.decodeFromString(this.serializer(), yamlText)
    }
}
