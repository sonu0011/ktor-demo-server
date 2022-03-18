plugins {
    application
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.serialization") version "1.6.10"

}

group = "com.example"
version = "0.0.1"

repositories {
    mavenCentral()
}
application {
//    mainClass.set("io.ktor.server.netty.EngineMain")
    mainClass.set("com.example.ApplicationKt")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-core:1.6.7")
    implementation("io.ktor:ktor-server-netty:1.6.7")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("io.ktor:ktor-serialization:1.6.7")

}