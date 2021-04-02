import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

plugins {
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.serialization") version "1.4.31"
    id("io.gitlab.arturbosch.detekt") version "1.15.0"
    id("org.jetbrains.dokka") version "1.4.20"
    application
}

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testImplementation(kotlin("test-junit"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    implementation("com.charleskorn.kaml:kaml:0.28.3")
    implementation("com.squareup:kotlinpoet:1.6.0")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.14.2")
}

detekt {
    failFast = true // fail build on any finding
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Werror")
    }
}

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        named("main") {
            moduleName.set("SPBU Kotlin homework")
            sourceLink {
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl.set(URL("https://github.com/Degiv/spbu_kotlin_homework/" +
                        "tree/master/src/main/kotlin"
                ))
                remoteLineSuffix.set("#L")
            }
        }
    }
}

application {
    mainClass.set("MainKt")
}
