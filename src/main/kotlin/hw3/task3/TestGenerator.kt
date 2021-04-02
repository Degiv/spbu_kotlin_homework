package hw3.task3

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ClassName

class TestGenerator(private val testGeneratorConfig: TestGeneratorConfig) {
    private val testClassName = "${testGeneratorConfig.className}Test"

    private fun createFunction(function: FunctionName) = FunSpec.builder(function.name)
        .addAnnotation(ClassName("org.junit.jupiter.api", "Test"))
        .build()

    private val createClass = TypeSpec.classBuilder(testClassName)
        .addModifiers(KModifier.INTERNAL)
        .addFunctions(testGeneratorConfig.functions.map { createFunction(it) })
        .build()

    val file: FileSpec = FileSpec.builder(testGeneratorConfig.packageName, testClassName)
        .addType(createClass)
        .build()
}
