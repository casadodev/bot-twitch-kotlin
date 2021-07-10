import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
    application
    id("org.jetbrains.kotlin.kapt") version "1.5.20"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "1.5.3"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.5.20"
}

group = "com.casadodev"
version = "1.0-SNAPSHOT"

val kotlinVersion= project.properties["kotlinVersion"]
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.suppressWarnings = true

repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.casadodev.*")
    }
}

dependencies {
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")

//    dependencia H2
//    implementation("com.h2database:h2:1.4.200")
    runtimeOnly("com.h2database:h2")

//    API Twicth
    implementation("com.github.twitch4j:twitch4j:1.5.0")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "16"
}

kotlin {
    sourceSets["main"].apply {
        kotlin.srcDir("src/main/kotlin")
    }
}

application {
    mainClass.set("com.casadodev.bootloader.ApplicationKt")
}
