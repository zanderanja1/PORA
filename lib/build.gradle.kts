plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}
dependencies {
    implementation("io.github.serpro69:kotlin-faker:1.15.0")
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
