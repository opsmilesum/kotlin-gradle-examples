// import org.unbrokendome.gradle.plugins.helm.publishing.tasks.HelmPublishChart

plugins {
    kotlin("jvm") version "1.6.10"
    application
//    id("org.unbroken-dome.helm")
//    id("org.unbroken-dome.helm-releases")
//    id("org.unbroken-dome.helm-publish")
}

group "org.example"
version "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.slf4j:slf4j-api:1.7.32")
    testImplementation("io.mockk:mockk:1.10.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    testImplementation("io.kotest:kotest-assertions-core:5.2.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.2.0")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
}

task("hello") {
    println("Hello, my first Gradle Project.")
}

tasks.test {
    useJUnitPlatform()
}