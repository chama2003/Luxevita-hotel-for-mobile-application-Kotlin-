// Root build file
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false  // Apply KSP plugin here
}

buildscript {
    val kotlin_version by extra("1.8.21") // Correct way to define extra properties in Kotlin DSL

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.4.1")  // Adjust if necessary
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.9.0-1.0.12")
    }
}
