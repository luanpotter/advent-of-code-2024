plugins {
    kotlin("jvm") version "2.0.21"
    id("io.gitlab.arturbosch.detekt") version "1.23.7"
}

group = "xyz.luan.advent"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.7")
    detektPlugins("com.braisgabin.detekt:kotlin-compiler-wrapper:0.0.4")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

detekt {
    autoCorrect = true
    config.from(".detekt.yaml")
}