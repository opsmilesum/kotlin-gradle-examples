plugins {
    java
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
}

group "org.example"
version "1.0-SNAPSHOT"

repositories {
    google()
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("junit:junit:4.12")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.4")
}

task("hello") {
    println("Hello, my first Gradle Project.")
}