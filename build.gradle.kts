val kotlinVersion: String by project

plugins {
    kotlin("jvm") version "1.9.21"
    id ("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}

version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.http4k:http4k-bom:5.11.1.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-netty")
    implementation("org.http4k:http4k-format-kotlinx-serialization")
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.11.0")
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
}
